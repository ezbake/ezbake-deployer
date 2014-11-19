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

public class BatchJobInfo implements org.apache.thrift.TBase<BatchJobInfo, BatchJobInfo._Fields>, java.io.Serializable, Cloneable, Comparable<BatchJobInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BatchJobInfo");

  private static final org.apache.thrift.protocol.TField START_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("startDate", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField START_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("startTime", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField REPEAT_FIELD_DESC = new org.apache.thrift.protocol.TField("repeat", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField FLOW_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("flowName", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new BatchJobInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new BatchJobInfoTupleSchemeFactory());
  }

  public String startDate; // optional
  public String startTime; // optional
  public String repeat; // optional
  public String flowName; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    START_DATE((short)1, "startDate"),
    START_TIME((short)2, "startTime"),
    REPEAT((short)3, "repeat"),
    FLOW_NAME((short)4, "flowName");

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
        case 1: // START_DATE
          return START_DATE;
        case 2: // START_TIME
          return START_TIME;
        case 3: // REPEAT
          return REPEAT;
        case 4: // FLOW_NAME
          return FLOW_NAME;
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
  private _Fields optionals[] = {_Fields.START_DATE,_Fields.START_TIME,_Fields.REPEAT,_Fields.FLOW_NAME};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.START_DATE, new org.apache.thrift.meta_data.FieldMetaData("startDate", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.START_TIME, new org.apache.thrift.meta_data.FieldMetaData("startTime", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REPEAT, new org.apache.thrift.meta_data.FieldMetaData("repeat", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FLOW_NAME, new org.apache.thrift.meta_data.FieldMetaData("flowName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BatchJobInfo.class, metaDataMap);
  }

  public BatchJobInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BatchJobInfo(BatchJobInfo other) {
    if (other.isSetStartDate()) {
      this.startDate = other.startDate;
    }
    if (other.isSetStartTime()) {
      this.startTime = other.startTime;
    }
    if (other.isSetRepeat()) {
      this.repeat = other.repeat;
    }
    if (other.isSetFlowName()) {
      this.flowName = other.flowName;
    }
  }

  public BatchJobInfo deepCopy() {
    return new BatchJobInfo(this);
  }

  @Override
  public void clear() {
    this.startDate = null;
    this.startTime = null;
    this.repeat = null;
    this.flowName = null;
  }

  public String getStartDate() {
    return this.startDate;
  }

  public BatchJobInfo setStartDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  public void unsetStartDate() {
    this.startDate = null;
  }

  /** Returns true if field startDate is set (has been assigned a value) and false otherwise */
  public boolean isSetStartDate() {
    return this.startDate != null;
  }

  public void setStartDateIsSet(boolean value) {
    if (!value) {
      this.startDate = null;
    }
  }

  public String getStartTime() {
    return this.startTime;
  }

  public BatchJobInfo setStartTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  public void unsetStartTime() {
    this.startTime = null;
  }

  /** Returns true if field startTime is set (has been assigned a value) and false otherwise */
  public boolean isSetStartTime() {
    return this.startTime != null;
  }

  public void setStartTimeIsSet(boolean value) {
    if (!value) {
      this.startTime = null;
    }
  }

  public String getRepeat() {
    return this.repeat;
  }

  public BatchJobInfo setRepeat(String repeat) {
    this.repeat = repeat;
    return this;
  }

  public void unsetRepeat() {
    this.repeat = null;
  }

  /** Returns true if field repeat is set (has been assigned a value) and false otherwise */
  public boolean isSetRepeat() {
    return this.repeat != null;
  }

  public void setRepeatIsSet(boolean value) {
    if (!value) {
      this.repeat = null;
    }
  }

  public String getFlowName() {
    return this.flowName;
  }

  public BatchJobInfo setFlowName(String flowName) {
    this.flowName = flowName;
    return this;
  }

  public void unsetFlowName() {
    this.flowName = null;
  }

  /** Returns true if field flowName is set (has been assigned a value) and false otherwise */
  public boolean isSetFlowName() {
    return this.flowName != null;
  }

  public void setFlowNameIsSet(boolean value) {
    if (!value) {
      this.flowName = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case START_DATE:
      if (value == null) {
        unsetStartDate();
      } else {
        setStartDate((String)value);
      }
      break;

    case START_TIME:
      if (value == null) {
        unsetStartTime();
      } else {
        setStartTime((String)value);
      }
      break;

    case REPEAT:
      if (value == null) {
        unsetRepeat();
      } else {
        setRepeat((String)value);
      }
      break;

    case FLOW_NAME:
      if (value == null) {
        unsetFlowName();
      } else {
        setFlowName((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case START_DATE:
      return getStartDate();

    case START_TIME:
      return getStartTime();

    case REPEAT:
      return getRepeat();

    case FLOW_NAME:
      return getFlowName();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case START_DATE:
      return isSetStartDate();
    case START_TIME:
      return isSetStartTime();
    case REPEAT:
      return isSetRepeat();
    case FLOW_NAME:
      return isSetFlowName();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof BatchJobInfo)
      return this.equals((BatchJobInfo)that);
    return false;
  }

  public boolean equals(BatchJobInfo that) {
    if (that == null)
      return false;

    boolean this_present_startDate = true && this.isSetStartDate();
    boolean that_present_startDate = true && that.isSetStartDate();
    if (this_present_startDate || that_present_startDate) {
      if (!(this_present_startDate && that_present_startDate))
        return false;
      if (!this.startDate.equals(that.startDate))
        return false;
    }

    boolean this_present_startTime = true && this.isSetStartTime();
    boolean that_present_startTime = true && that.isSetStartTime();
    if (this_present_startTime || that_present_startTime) {
      if (!(this_present_startTime && that_present_startTime))
        return false;
      if (!this.startTime.equals(that.startTime))
        return false;
    }

    boolean this_present_repeat = true && this.isSetRepeat();
    boolean that_present_repeat = true && that.isSetRepeat();
    if (this_present_repeat || that_present_repeat) {
      if (!(this_present_repeat && that_present_repeat))
        return false;
      if (!this.repeat.equals(that.repeat))
        return false;
    }

    boolean this_present_flowName = true && this.isSetFlowName();
    boolean that_present_flowName = true && that.isSetFlowName();
    if (this_present_flowName || that_present_flowName) {
      if (!(this_present_flowName && that_present_flowName))
        return false;
      if (!this.flowName.equals(that.flowName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(BatchJobInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetStartDate()).compareTo(other.isSetStartDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startDate, other.startDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStartTime()).compareTo(other.isSetStartTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startTime, other.startTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRepeat()).compareTo(other.isSetRepeat());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRepeat()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.repeat, other.repeat);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFlowName()).compareTo(other.isSetFlowName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFlowName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.flowName, other.flowName);
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
    StringBuilder sb = new StringBuilder("BatchJobInfo(");
    boolean first = true;

    if (isSetStartDate()) {
      sb.append("startDate:");
      if (this.startDate == null) {
        sb.append("null");
      } else {
        sb.append(this.startDate);
      }
      first = false;
    }
    if (isSetStartTime()) {
      if (!first) sb.append(", ");
      sb.append("startTime:");
      if (this.startTime == null) {
        sb.append("null");
      } else {
        sb.append(this.startTime);
      }
      first = false;
    }
    if (isSetRepeat()) {
      if (!first) sb.append(", ");
      sb.append("repeat:");
      if (this.repeat == null) {
        sb.append("null");
      } else {
        sb.append(this.repeat);
      }
      first = false;
    }
    if (isSetFlowName()) {
      if (!first) sb.append(", ");
      sb.append("flowName:");
      if (this.flowName == null) {
        sb.append("null");
      } else {
        sb.append(this.flowName);
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

  private static class BatchJobInfoStandardSchemeFactory implements SchemeFactory {
    public BatchJobInfoStandardScheme getScheme() {
      return new BatchJobInfoStandardScheme();
    }
  }

  private static class BatchJobInfoStandardScheme extends StandardScheme<BatchJobInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BatchJobInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // START_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.startDate = iprot.readString();
              struct.setStartDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // START_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.startTime = iprot.readString();
              struct.setStartTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // REPEAT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.repeat = iprot.readString();
              struct.setRepeatIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // FLOW_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.flowName = iprot.readString();
              struct.setFlowNameIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, BatchJobInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.startDate != null) {
        if (struct.isSetStartDate()) {
          oprot.writeFieldBegin(START_DATE_FIELD_DESC);
          oprot.writeString(struct.startDate);
          oprot.writeFieldEnd();
        }
      }
      if (struct.startTime != null) {
        if (struct.isSetStartTime()) {
          oprot.writeFieldBegin(START_TIME_FIELD_DESC);
          oprot.writeString(struct.startTime);
          oprot.writeFieldEnd();
        }
      }
      if (struct.repeat != null) {
        if (struct.isSetRepeat()) {
          oprot.writeFieldBegin(REPEAT_FIELD_DESC);
          oprot.writeString(struct.repeat);
          oprot.writeFieldEnd();
        }
      }
      if (struct.flowName != null) {
        if (struct.isSetFlowName()) {
          oprot.writeFieldBegin(FLOW_NAME_FIELD_DESC);
          oprot.writeString(struct.flowName);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BatchJobInfoTupleSchemeFactory implements SchemeFactory {
    public BatchJobInfoTupleScheme getScheme() {
      return new BatchJobInfoTupleScheme();
    }
  }

  private static class BatchJobInfoTupleScheme extends TupleScheme<BatchJobInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BatchJobInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetStartDate()) {
        optionals.set(0);
      }
      if (struct.isSetStartTime()) {
        optionals.set(1);
      }
      if (struct.isSetRepeat()) {
        optionals.set(2);
      }
      if (struct.isSetFlowName()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetStartDate()) {
        oprot.writeString(struct.startDate);
      }
      if (struct.isSetStartTime()) {
        oprot.writeString(struct.startTime);
      }
      if (struct.isSetRepeat()) {
        oprot.writeString(struct.repeat);
      }
      if (struct.isSetFlowName()) {
        oprot.writeString(struct.flowName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BatchJobInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.startDate = iprot.readString();
        struct.setStartDateIsSet(true);
      }
      if (incoming.get(1)) {
        struct.startTime = iprot.readString();
        struct.setStartTimeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.repeat = iprot.readString();
        struct.setRepeatIsSet(true);
      }
      if (incoming.get(3)) {
        struct.flowName = iprot.readString();
        struct.setFlowNameIsSet(true);
      }
    }
  }

}

