package io.ffreedom.persistence.avro.serializable;

import org.apache.avro.specific.SpecificDatumReader;
import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;

public abstract class BaseAvroDeserializer<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SpecificDatumReader<T> datumReader;

	protected final SpecificDatumReader<T> initDatumReader(Class<T> tClass) {
		return new SpecificDatumReader<>(tClass);
	}

}
