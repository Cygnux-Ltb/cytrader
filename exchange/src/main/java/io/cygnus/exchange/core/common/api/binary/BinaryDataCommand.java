package io.cygnus.exchange.core.common.api.binary;

import net.openhft.chronicle.bytes.WriteBytesMarshallable;

public interface BinaryDataCommand extends WriteBytesMarshallable {

    int getBinaryCommandTypeCode();

}
