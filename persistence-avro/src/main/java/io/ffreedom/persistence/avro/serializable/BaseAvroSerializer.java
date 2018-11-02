package io.ffreedom.persistence.avro.serializable;

import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;

public abstract class BaseAvroSerializer {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected ByteArrayOutputStream outputStream;

	protected DatumWriter<SpecificRecord> writer;

	/**
	 * 
	 * @param size
	 *            : size is inner ByteArrayOutputStream size
	 */
	protected BaseAvroSerializer(int size) {
		this.outputStream = new ByteArrayOutputStream(size);
		this.writer = new SpecificDatumWriter<SpecificRecord>();
	}

}
