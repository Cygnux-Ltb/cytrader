package io.ffreedom.persistence.avro.serializable;

import java.io.IOException;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.JsonDecoder;
import org.apache.avro.specific.SpecificRecord;

import io.ffreedom.common.serialization.TextDeserializer;

@NotThreadSafe
public final class AvroTextDeserializer<T extends SpecificRecord> extends BaseAvroDeserializer<T>
		implements TextDeserializer<T> {

	private JsonDecoder decoder;

	private Class<T> tClass;

	public AvroTextDeserializer(Class<T> tClass) {
		this.tClass = tClass;
	}

	@Override
	public T deSerializationSingle(String str) {
		try {
			if (datumReader == null) {
				datumReader = initDatumReader(tClass);
			}
			if (decoder == null) {
				decoder = initDecoder(datumReader.getSchema());
			}
			decoder.configure(str);
			return datumReader.read(null, decoder);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("AvroTextDeserializer.deSerialization(str, tClass) -> " + e.getMessage());
		}
	}

	private JsonDecoder initDecoder(Schema schema) throws IOException {
		return DecoderFactory.get().jsonDecoder(schema, "");
	}

	@Override
	public List<T> deSerializationMultiple(String f) {
		throw new AvroRuntimeException("deSerializationMultiple() -> " + f);
	}

}
