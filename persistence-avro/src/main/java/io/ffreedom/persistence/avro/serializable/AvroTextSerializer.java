package io.ffreedom.persistence.avro.serializable;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.specific.SpecificRecord;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.serialization.TextSerializer;

@NotThreadSafe
public class AvroTextSerializer extends BaseAvroSerializer implements TextSerializer<SpecificRecord> {

	private JsonEncoder encoder;

	/**
	 * Use default ByteArrayOutputStream size
	 */
	public AvroTextSerializer() {
		this(1024 * 8);
	}

	public AvroTextSerializer(int size) {
		super(size);
	}

	@Override
	public String serialization(SpecificRecord record) {
		writer.setSchema(record.getSchema());

		try {
			encoder = EncoderFactory.get().jsonEncoder(record.getSchema(), outputStream);
			writer.write(record, encoder);
			encoder.flush();
			byte[] bytes = outputStream.toByteArray();
			outputStream.reset();
			return new String(bytes, Charsets.UTF8);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			try {
				outputStream.close();
			} catch (IOException e1) {
				logger.error(e1.getMessage(), e);
			}
			throw new RuntimeException("AvroTextSerializer.serialization(record) -> " + e.getMessage());
		}

	}

}
