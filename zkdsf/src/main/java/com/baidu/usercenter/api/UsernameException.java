/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.baidu.usercenter.api;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class UsernameException extends org.apache.avro.specific.SpecificExceptionBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"error\",\"name\":\"UsernameException\",\"namespace\":\"com.baidu.usercenter.api\",\"fields\":[]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  public UsernameException() {
    super();
  }
  
  public UsernameException(Object value) {
    super(value);
  }

  public UsernameException(Throwable cause) {
    super(cause);
  }

  public UsernameException(Object value, Throwable cause) {
    super(value, cause);
  }
  
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /** Creates a new UsernameException RecordBuilder */
  public static com.baidu.usercenter.api.UsernameException.Builder newBuilder() {
    return new com.baidu.usercenter.api.UsernameException.Builder();
  }
  
  /** Creates a new UsernameException RecordBuilder by copying an existing Builder */
  public static com.baidu.usercenter.api.UsernameException.Builder newBuilder(com.baidu.usercenter.api.UsernameException.Builder other) {
    return new com.baidu.usercenter.api.UsernameException.Builder(other);
  }
  
  /** Creates a new UsernameException RecordBuilder by copying an existing UsernameException instance */
  public static com.baidu.usercenter.api.UsernameException.Builder newBuilder(com.baidu.usercenter.api.UsernameException other) {
    return new com.baidu.usercenter.api.UsernameException.Builder(other);
  }
  
  /**
   * RecordBuilder for UsernameException instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificErrorBuilderBase<UsernameException>
    implements org.apache.avro.data.ErrorBuilder<UsernameException> {


    /** Creates a new Builder */
    private Builder() {
      super(com.baidu.usercenter.api.UsernameException.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.baidu.usercenter.api.UsernameException.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing UsernameException instance */
    private Builder(com.baidu.usercenter.api.UsernameException other) {
      super(other);
    }

    @Override
    public com.baidu.usercenter.api.UsernameException.Builder setValue(Object value) {
      super.setValue(value);
      return this;
    }
    
    @Override
    public com.baidu.usercenter.api.UsernameException.Builder clearValue() {
      super.clearValue();
      return this;
    }

    @Override
    public com.baidu.usercenter.api.UsernameException.Builder setCause(Throwable cause) {
      super.setCause(cause);
      return this;
    }
    
    @Override
    public com.baidu.usercenter.api.UsernameException.Builder clearCause() {
      super.clearCause();
      return this;
    }

    @Override
    public UsernameException build() {
      try {
        UsernameException record = new UsernameException(getValue(), getCause());
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}