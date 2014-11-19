/*   Copyright (C) 2013-2014 Computer Sciences Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package ezbake.deployer.utilities;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import ezbake.services.deploy.thrift.DeploymentException;
import org.apache.commons.compress.archivers.*;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.meta_data.FieldMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

public class Utilities {
    private static final Logger log = LoggerFactory.getLogger(Utilities.class);

    public static final String CONFIG_DIRECTORY = "config";
    public static final String SSL_CONFIG_DIRECTORY = CONFIG_DIRECTORY + "/ssl";

    public static void appendFilesInTarArchive(OutputStream output, Iterable<CertDataEntry> filesToAdd) throws DeploymentException {
        ArchiveStreamFactory asf = new ArchiveStreamFactory();
        try {
            GZIPOutputStream gzs = new GZIPOutputStream(output);
            ArchiveOutputStream aos = asf.createArchiveOutputStream(ArchiveStreamFactory.TAR, gzs);

            for (CertDataEntry entry : filesToAdd) {
                aos.putArchiveEntry(entry.getEntry());
                IOUtils.write(entry.getData(), aos);
                aos.closeArchiveEntry();
            }
            aos.finish();
            gzs.finish();
        } catch (ArchiveException e) {
            log.error(e.getMessage(), e);
            throw new DeploymentException(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new DeploymentException(e.getMessage());
        }
    }

    public static String s(String format, Object ... args) {
        return String.format(format, args);
    }

    public static SSLCertsService nullCertService() {
        return new SSLCertsService() {
            @Override
            public List<CertDataEntry> get(String applicationId, String securityId) throws DeploymentException {
                return Lists.newArrayList();
            }
        };
    }

    public static String prettyPrintThriftObject(TBase obj) {
        return obj == null ? "null" : ppTBaseObject(obj, 0);
    }

    public static CertDataEntry createConfCertDataEntry(String fileName, byte [] data) {
        return new CertDataEntry(new TarArchiveEntry(String.format("%s/%s", CONFIG_DIRECTORY, fileName)), data);
    }


    private static String ppTBaseObject(TBase t, int depth) {
        List<String> fields = Lists.newArrayList();
        final String indent = Strings.repeat("  ", depth);
        for (Map.Entry<? extends TFieldIdEnum, FieldMetaData> entry :
                FieldMetaData.getStructMetaDataMap(t.getClass()).entrySet()) {
            fields.add(indent + entry.getValue().fieldName + ": " + ppTBaseField(t, entry, depth));
        }
        return Joiner.on("\n").join(fields);
    }

    private static String ppTBaseField(TBase base, Map.Entry<? extends TFieldIdEnum, FieldMetaData> entry, int depth) {
        String strValue;
        //noinspection unchecked
        if ( base.isSet(entry.getKey()) ) {
            @SuppressWarnings("unchecked")
            Object value = base.getFieldValue(entry.getKey());
            strValue = ppObject(value, depth);
        } else {
            strValue = "not set";
        }
        return strValue;
    }
    private static String ppObject(Object o, int depth) {
        if (o == null) {
            return "null";
        } else if (o instanceof TBase ) {
            return "\n" + ppTBaseObject((TBase) o, depth + 1);
        } else if (o instanceof Map) {
            return ppMap((Map) o, depth + 1);
        } else if (o instanceof List) {
            return ppList((List) o, depth + 1);
        } else if (o instanceof Set) {
            return ppSet((Set) o, depth + 1);
        } else if (o instanceof String) {
            return '"' + o.toString() + '"';
        } else { /* number */
            return o.toString();
        }
    }
    private static String ppSet(Set<?> set, int depth) {
        List<String> entries = Lists.newArrayList();
        final String indent = Strings.repeat("  ", depth);
        for (Object item : set) {
            entries.add(indent + ppObject(item, depth));
        }

        return entries.isEmpty() ? "{}"
                : s("{\n%s\n%s}", Joiner.on(",\n").join(entries), Strings.repeat("  ", depth - 1));
    }
    private static String ppList(List<?> list, int depth) {
        List<String> entries = Lists.newArrayList();
        final String indent = Strings.repeat("  ", depth);
        for (int i = 0; i < list.size(); i++) {
            entries.add(s("%s[%d] = %s", indent, i, ppObject(list.get(i), depth)));
        }

        return entries.isEmpty() ? "[]"
                : s("[\n%s\n%s]", Joiner.on(",\n").join(entries), Strings.repeat("  ", depth - 1));
    }
    private static String ppMap(Map<?, ?> map, int depth) {
        List<String> entries = Lists.newArrayList();
        final String indent = Strings.repeat("  ", depth);
        for (Map.Entry entry : map.entrySet()) {
            entries.add(indent + ppObject(entry.getKey(), depth)
                    + " = " + ppObject(entry.getValue(), depth));
        }

        return entries.isEmpty() ? "{}"
                : s("{\n%s\n%s}", Joiner.on(",\n").join(entries), Strings.repeat("  ", depth - 1));
    }


}
