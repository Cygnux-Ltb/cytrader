package io.ffreedom.persistence.avro.serializable;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecord;

import io.ffreedom.common.serialization.BytesSerializer;

@NotThreadSafe
public final class AvroBytesSerializer extends BaseAvroSerializer implements BytesSerializer<SpecificRecord> {

	private BinaryEncoder encoder;

	/**
	 * Use default ByteArrayOutputStream size
	 */
	public AvroBytesSerializer() {
		this(1024 * 8);
	}

	public AvroBytesSerializer(int size) {
		super(size);
	}

	@Override
	public byte[] serialization(SpecificRecord record) {
		writer.setSchema(record.getSchema());
		try {
			encoder = EncoderFactory.get().binaryEncoder(outputStream, encoder);
			writer.write(record, encoder);
			encoder.flush();
			byte[] bytes = outputStream.toByteArray();
			outputStream.reset();
			return bytes;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			try {
				outputStream.close();
			} catch (IOException e1) {
				logger.error(e1.getMessage(), e);
			}
			throw new RuntimeException("AvroBytesSerializer.serialization(record) -> " + e.getMessage());
		}
	}

}
