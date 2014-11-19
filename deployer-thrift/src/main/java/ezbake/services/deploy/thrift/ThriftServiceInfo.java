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

public class ThriftServiceInfo implements org.apache.thrift.TBase<ThriftServiceInfo, ThriftServiceInfo._Fields>, java.io.Serializable, Cloneable, Comparable<ThriftServiceInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThriftServiceInfo");

  private static final org.apache.thrift.protocol.TField RESERVED_FIELD_DESC = new org.apache.thrift.protocol.TField("reserved", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ThriftServiceInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ThriftServiceInfoTupleSchemeFactory());
  }

  public String reserved; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESERVED((short)1, "reserved");

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
        case 1: // RESERVED
          return RESERVED;
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
  private _Fields optionals[] = {_Fields.RESERVED};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RESERVED, new org.apache.thrift.meta_data.FieldMetaData("reserved", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThriftServiceInfo.class, metaDataMap);
  }

  public ThriftServiceInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThriftServiceInfo(ThriftServiceInfo other) {
    if (other.isSetReserved()) {
      this.reserved = other.reserved;
    }
  }

  public ThriftServiceInfo deepCopy() {
    return new ThriftServiceInfo(this);
  }

  @Override
  public void clear() {
    this.reserved = null;
  }

  public String getReserved() {
    return this.reserved;
  }

  public ThriftServiceInfo setReserved(String reserved) {
    this.reserved = reserved;
    return this;
  }

  public void unsetReserved() {
    this.reserved = null;
  }

  /** Returns true if field reserved is set (has been assigned a value) and false otherwise */
  public boolean isSetReserved() {
    return this.reserved != null;
  }

  public void setReservedIsSet(boolean value) {
    if (!value) {
      this.reserved = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RESERVED:
      if (value == null) {
        unsetReserved();
      } else {
        setReserved((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RESERVED:
      return getReserved();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RESERVED:
      return isSetReserved();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftServiceInfo)
      return this.equals((ThriftServiceInfo)that);
    return false;
  }

  public boolean equals(ThriftServiceInfo that) {
    if (that == null)
      return false;

    boolean this_present_reserved = true && this.isSetReserved();
    boolean that_present_reserved = true && that.isSetReserved();
    if (this_present_reserved || that_present_reserved) {
      if (!(this_present_reserved && that_present_reserved))
        return false;
      if (!this.reserved.equals(that.reserved))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(ThriftServiceInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetReserved()).compareTo(other.isSetReserved());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReserved()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.reserved, other.reserved);
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
    StringBuilder sb = new StringBuilder("ThriftServiceInfo(");
    boolean first = true;

    if (isSetReserved()) {
      sb.append("reserved:");
      if (this.reserved == null) {
        sb.append("null");
      } else {
        sb.append(this.reserved);
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

  private static class ThriftServiceInfoStandardSchemeFactory implements SchemeFactory {
    public ThriftServiceInfoStandardScheme getScheme() {
      return new ThriftServiceInfoStandardScheme();
    }
  }

  private static class ThriftServiceInfoStandardScheme extends StandardScheme<ThriftServiceInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThriftServiceInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RESERVED
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.reserved = iprot.readString();
              struct.setReservedIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThriftServiceInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.reserved != null) {
        if (struct.isSetReserved()) {
          oprot.writeFieldBegin(RESERVED_FIELD_DESC);
          oprot.writeString(struct.reserved);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThriftServiceInfoTupleSchemeFactory implements SchemeFactory {
    public ThriftServiceInfoTupleScheme getScheme() {
      return new ThriftServiceInfoTupleScheme();
    }
  }

  private static class ThriftServiceInfoTupleScheme extends TupleScheme<ThriftServiceInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThriftServiceInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetReserved()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetReserved()) {
        oprot.writeString(struct.reserved);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThriftServiceInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.reserved = iprot.readString();
        struct.setReservedIsSet(true);
      }
    }
  }

}
