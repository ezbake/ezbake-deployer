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

/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package ezbake.services.deploy.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationInfo implements org.apache.thrift.TBase<ApplicationInfo, ApplicationInfo._Fields>, java.io.Serializable, Cloneable, Comparable<ApplicationInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ApplicationInfo");

  private static final org.apache.thrift.protocol.TField APPLICATION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("applicationId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField SERVICE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("serviceId", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DATASETS_FIELD_DESC = new org.apache.thrift.protocol.TField("datasets", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField SECURITY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("securityId", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField AUTHS_FIELD_DESC = new org.apache.thrift.protocol.TField("auths", org.apache.thrift.protocol.TType.SET, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ApplicationInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ApplicationInfoTupleSchemeFactory());
  }

  public String applicationId; // optional
  public String serviceId; // optional
  public List<String> datasets; // optional
  public String securityId; // optional
  public Set<String> auths; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APPLICATION_ID((short)1, "applicationId"),
    SERVICE_ID((short)2, "serviceId"),
    DATASETS((short)3, "datasets"),
    SECURITY_ID((short)4, "securityId"),
    AUTHS((short)5, "auths");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // APPLICATION_ID
          return APPLICATION_ID;
        case 2: // SERVICE_ID
          return SERVICE_ID;
        case 3: // DATASETS
          return DATASETS;
        case 4: // SECURITY_ID
          return SECURITY_ID;
        case 5: // AUTHS
          return AUTHS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private _Fields optionals[] = {_Fields.APPLICATION_ID,_Fields.SERVICE_ID,_Fields.DATASETS,_Fields.SECURITY_ID,_Fields.AUTHS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.APPLICATION_ID, new org.apache.thrift.meta_data.FieldMetaData("applicationId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SERVICE_ID, new org.apache.thrift.meta_data.FieldMetaData("serviceId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATASETS, new org.apache.thrift.meta_data.FieldMetaData("datasets", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.SECURITY_ID, new org.apache.thrift.meta_data.FieldMetaData("securityId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AUTHS, new org.apache.thrift.meta_data.FieldMetaData("auths", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ApplicationInfo.class, metaDataMap);
  }

  public ApplicationInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ApplicationInfo(ApplicationInfo other) {
    if (other.isSetApplicationId()) {
      this.applicationId = other.applicationId;
    }
    if (other.isSetServiceId()) {
      this.serviceId = other.serviceId;
    }
    if (other.isSetDatasets()) {
      List<String> __this__datasets = new ArrayList<String>(other.datasets);
      this.datasets = __this__datasets;
    }
    if (other.isSetSecurityId()) {
      this.securityId = other.securityId;
    }
    if (other.isSetAuths()) {
      Set<String> __this__auths = new HashSet<String>(other.auths);
      this.auths = __this__auths;
    }
  }

  public ApplicationInfo deepCopy() {
    return new ApplicationInfo(this);
  }

  @Override
  public void clear() {
    this.applicationId = null;
    this.serviceId = null;
    this.datasets = null;
    this.securityId = null;
    this.auths = null;
  }

  public String getApplicationId() {
    return this.applicationId;
  }

  public ApplicationInfo setApplicationId(String applicationId) {
    this.applicationId = applicationId;
    return this;
  }

  public void unsetApplicationId() {
    this.applicationId = null;
  }

  /** Returns true if field applicationId is set (has been assigned a value) and false otherwise */
  public boolean isSetApplicationId() {
    return this.applicationId != null;
  }

  public void setApplicationIdIsSet(boolean value) {
    if (!value) {
      this.applicationId = null;
    }
  }

  public String getServiceId() {
    return this.serviceId;
  }

  public ApplicationInfo setServiceId(String serviceId) {
    this.serviceId = serviceId;
    return this;
  }

  public void unsetServiceId() {
    this.serviceId = null;
  }

  /** Returns true if field serviceId is set (has been assigned a value) and false otherwise */
  public boolean isSetServiceId() {
    return this.serviceId != null;
  }

  public void setServiceIdIsSet(boolean value) {
    if (!value) {
      this.serviceId = null;
    }
  }

  public int getDatasetsSize() {
    return (this.datasets == null) ? 0 : this.datasets.size();
  }

  public java.util.Iterator<String> getDatasetsIterator() {
    return (this.datasets == null) ? null : this.datasets.iterator();
  }

  public void addToDatasets(String elem) {
    if (this.datasets == null) {
      this.datasets = new ArrayList<String>();
    }
    this.datasets.add(elem);
  }

  public List<String> getDatasets() {
    return this.datasets;
  }

  public ApplicationInfo setDatasets(List<String> datasets) {
    this.datasets = datasets;
    return this;
  }

  public void unsetDatasets() {
    this.datasets = null;
  }

  /** Returns true if field datasets is set (has been assigned a value) and false otherwise */
  public boolean isSetDatasets() {
    return this.datasets != null;
  }

  public void setDatasetsIsSet(boolean value) {
    if (!value) {
      this.datasets = null;
    }
  }

  public String getSecurityId() {
    return this.securityId;
  }

  public ApplicationInfo setSecurityId(String securityId) {
    this.securityId = securityId;
    return this;
  }

  public void unsetSecurityId() {
    this.securityId = null;
  }

  /** Returns true if field securityId is set (has been assigned a value) and false otherwise */
  public boolean isSetSecurityId() {
    return this.securityId != null;
  }

  public void setSecurityIdIsSet(boolean value) {
    if (!value) {
      this.securityId = null;
    }
  }

  public int getAuthsSize() {
    return (this.auths == null) ? 0 : this.auths.size();
  }

  public java.util.Iterator<String> getAuthsIterator() {
    return (this.auths == null) ? null : this.auths.iterator();
  }

  public void addToAuths(String elem) {
    if (this.auths == null) {
      this.auths = new HashSet<String>();
    }
    this.auths.add(elem);
  }

  public Set<String> getAuths() {
    return this.auths;
  }

  public ApplicationInfo setAuths(Set<String> auths) {
    this.auths = auths;
    return this;
  }

  public void unsetAuths() {
    this.auths = null;
  }

  /** Returns true if field auths is set (has been assigned a value) and false otherwise */
  public boolean isSetAuths() {
    return this.auths != null;
  }

  public void setAuthsIsSet(boolean value) {
    if (!value) {
      this.auths = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case APPLICATION_ID:
      if (value == null) {
        unsetApplicationId();
      } else {
        setApplicationId((String)value);
      }
      break;

    case SERVICE_ID:
      if (value == null) {
        unsetServiceId();
      } else {
        setServiceId((String)value);
      }
      break;

    case DATASETS:
      if (value == null) {
        unsetDatasets();
      } else {
        setDatasets((List<String>)value);
      }
      break;

    case SECURITY_ID:
      if (value == null) {
        unsetSecurityId();
      } else {
        setSecurityId((String)value);
      }
      break;

    case AUTHS:
      if (value == null) {
        unsetAuths();
      } else {
        setAuths((Set<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case APPLICATION_ID:
      return getApplicationId();

    case SERVICE_ID:
      return getServiceId();

    case DATASETS:
      return getDatasets();

    case SECURITY_ID:
      return getSecurityId();

    case AUTHS:
      return getAuths();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case APPLICATION_ID:
      return isSetApplicationId();
    case SERVICE_ID:
      return isSetServiceId();
    case DATASETS:
      return isSetDatasets();
    case SECURITY_ID:
      return isSetSecurityId();
    case AUTHS:
      return isSetAuths();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ApplicationInfo)
      return this.equals((ApplicationInfo)that);
    return false;
  }

  public boolean equals(ApplicationInfo that) {
    if (that == null)
      return false;

    boolean this_present_applicationId = true && this.isSetApplicationId();
    boolean that_present_applicationId = true && that.isSetApplicationId();
    if (this_present_applicationId || that_present_applicationId) {
      if (!(this_present_applicationId && that_present_applicationId))
        return false;
      if (!this.applicationId.equals(that.applicationId))
        return false;
    }

    boolean this_present_serviceId = true && this.isSetServiceId();
    boolean that_present_serviceId = true && that.isSetServiceId();
    if (this_present_serviceId || that_present_serviceId) {
      if (!(this_present_serviceId && that_present_serviceId))
        return false;
      if (!this.serviceId.equals(that.serviceId))
        return false;
    }

    boolean this_present_datasets = true && this.isSetDatasets();
    boolean that_present_datasets = true && that.isSetDatasets();
    if (this_present_datasets || that_present_datasets) {
      if (!(this_present_datasets && that_present_datasets))
        return false;
      if (!this.datasets.equals(that.datasets))
        return false;
    }

    boolean this_present_securityId = true && this.isSetSecurityId();
    boolean that_present_securityId = true && that.isSetSecurityId();
    if (this_present_securityId || that_present_securityId) {
      if (!(this_present_securityId && that_present_securityId))
        return false;
      if (!this.securityId.equals(that.securityId))
        return false;
    }

    boolean this_present_auths = true && this.isSetAuths();
    boolean that_present_auths = true && that.isSetAuths();
    if (this_present_auths || that_present_auths) {
      if (!(this_present_auths && that_present_auths))
        return false;
      if (!this.auths.equals(that.auths))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(ApplicationInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetApplicationId()).compareTo(other.isSetApplicationId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetApplicationId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.applicationId, other.applicationId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetServiceId()).compareTo(other.isSetServiceId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServiceId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serviceId, other.serviceId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDatasets()).compareTo(other.isSetDatasets());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDatasets()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.datasets, other.datasets);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSecurityId()).compareTo(other.isSetSecurityId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSecurityId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.securityId, other.securityId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAuths()).compareTo(other.isSetAuths());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAuths()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.auths, other.auths);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ApplicationInfo(");
    boolean first = true;

    if (isSetApplicationId()) {
      sb.append("applicationId:");
      if (this.applicationId == null) {
        sb.append("null");
      } else {
        sb.append(this.applicationId);
      }
      first = false;
    }
    if (isSetServiceId()) {
      if (!first) sb.append(", ");
      sb.append("serviceId:");
      if (this.serviceId == null) {
        sb.append("null");
      } else {
        sb.append(this.serviceId);
      }
      first = false;
    }
    if (isSetDatasets()) {
      if (!first) sb.append(", ");
      sb.append("datasets:");
      if (this.datasets == null) {
        sb.append("null");
      } else {
        sb.append(this.datasets);
      }
      first = false;
    }
    if (isSetSecurityId()) {
      if (!first) sb.append(", ");
      sb.append("securityId:");
      if (this.securityId == null) {
        sb.append("null");
      } else {
        sb.append(this.securityId);
      }
      first = false;
    }
    if (isSetAuths()) {
      if (!first) sb.append(", ");
      sb.append("auths:");
      if (this.auths == null) {
        sb.append("null");
      } else {
        sb.append(this.auths);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ApplicationInfoStandardSchemeFactory implements SchemeFactory {
    public ApplicationInfoStandardScheme getScheme() {
      return new ApplicationInfoStandardScheme();
    }
  }

  private static class ApplicationInfoStandardScheme extends StandardScheme<ApplicationInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ApplicationInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // APPLICATION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.applicationId = iprot.readString();
              struct.setApplicationIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SERVICE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.serviceId = iprot.readString();
              struct.setServiceIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATASETS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.datasets = new ArrayList<String>(_list0.size);
                for (int _i1 = 0; _i1 < _list0.size; ++_i1)
                {
                  String _elem2;
                  _elem2 = iprot.readString();
                  struct.datasets.add(_elem2);
                }
                iprot.readListEnd();
              }
              struct.setDatasetsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SECURITY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.securityId = iprot.readString();
              struct.setSecurityIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // AUTHS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set3 = iprot.readSetBegin();
                struct.auths = new HashSet<String>(2*_set3.size);
                for (int _i4 = 0; _i4 < _set3.size; ++_i4)
                {
                  String _elem5;
                  _elem5 = iprot.readString();
                  struct.auths.add(_elem5);
                }
                iprot.readSetEnd();
              }
              struct.setAuthsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ApplicationInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.applicationId != null) {
        if (struct.isSetApplicationId()) {
          oprot.writeFieldBegin(APPLICATION_ID_FIELD_DESC);
          oprot.writeString(struct.applicationId);
          oprot.writeFieldEnd();
        }
      }
      if (struct.serviceId != null) {
        if (struct.isSetServiceId()) {
          oprot.writeFieldBegin(SERVICE_ID_FIELD_DESC);
          oprot.writeString(struct.serviceId);
          oprot.writeFieldEnd();
        }
      }
      if (struct.datasets != null) {
        if (struct.isSetDatasets()) {
          oprot.writeFieldBegin(DATASETS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.datasets.size()));
            for (String _iter6 : struct.datasets)
            {
              oprot.writeString(_iter6);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.securityId != null) {
        if (struct.isSetSecurityId()) {
          oprot.writeFieldBegin(SECURITY_ID_FIELD_DESC);
          oprot.writeString(struct.securityId);
          oprot.writeFieldEnd();
        }
      }
      if (struct.auths != null) {
        if (struct.isSetAuths()) {
          oprot.writeFieldBegin(AUTHS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, struct.auths.size()));
            for (String _iter7 : struct.auths)
            {
              oprot.writeString(_iter7);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ApplicationInfoTupleSchemeFactory implements SchemeFactory {
    public ApplicationInfoTupleScheme getScheme() {
      return new ApplicationInfoTupleScheme();
    }
  }

  private static class ApplicationInfoTupleScheme extends TupleScheme<ApplicationInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ApplicationInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetApplicationId()) {
        optionals.set(0);
      }
      if (struct.isSetServiceId()) {
        optionals.set(1);
      }
      if (struct.isSetDatasets()) {
        optionals.set(2);
      }
      if (struct.isSetSecurityId()) {
        optionals.set(3);
      }
      if (struct.isSetAuths()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetApplicationId()) {
        oprot.writeString(struct.applicationId);
      }
      if (struct.isSetServiceId()) {
        oprot.writeString(struct.serviceId);
      }
      if (struct.isSetDatasets()) {
        {
          oprot.writeI32(struct.datasets.size());
          for (String _iter8 : struct.datasets)
          {
            oprot.writeString(_iter8);
          }
        }
      }
      if (struct.isSetSecurityId()) {
        oprot.writeString(struct.securityId);
      }
      if (struct.isSetAuths()) {
        {
          oprot.writeI32(struct.auths.size());
          for (String _iter9 : struct.auths)
          {
            oprot.writeString(_iter9);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ApplicationInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.applicationId = iprot.readString();
        struct.setApplicationIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.serviceId = iprot.readString();
        struct.setServiceIdIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list10 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.datasets = new ArrayList<String>(_list10.size);
          for (int _i11 = 0; _i11 < _list10.size; ++_i11)
          {
            String _elem12;
            _elem12 = iprot.readString();
            struct.datasets.add(_elem12);
          }
        }
        struct.setDatasetsIsSet(true);
      }
      if (incoming.get(3)) {
        struct.securityId = iprot.readString();
        struct.setSecurityIdIsSet(true);
      }
      if (incoming.get(4)) {
        {
          org.apache.thrift.protocol.TSet _set13 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.auths = new HashSet<String>(2*_set13.size);
          for (int _i14 = 0; _i14 < _set13.size; ++_i14)
          {
            String _elem15;
            _elem15 = iprot.readString();
            struct.auths.add(_elem15);
          }
        }
        struct.setAuthsIsSet(true);
      }
    }
  }

}

