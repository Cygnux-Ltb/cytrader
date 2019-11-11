package io.ffreedom.persistence.avro.serializable;

import org.apache.avro.specific.SpecificDatumReader;
import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;

public abstract class BaseAvroDeserializer<T> {

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	protected SpecificDatumReader<T> datumReader;

	protected final SpecificDatumReader<T> initDatumReader(Class<T> tClass) {
		return new SpecificDatumReader<>(tClass);
	}

}
