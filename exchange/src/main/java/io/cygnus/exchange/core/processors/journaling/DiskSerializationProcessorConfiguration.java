/**
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
 * limitations under the License.
 * 
 */
package io.cygnus.exchange.core.processors.journaling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

import java.util.function.Supplier;

@Builder
@AllArgsConstructor
public class DiskSerializationProcessorConfiguration {

	public static final String DEFAULT_FOLDER = "./dumps";

	private static final long ONE_MEGABYTE = 1024 * 1024;

	public static final Supplier<LZ4Compressor> LZ4_FAST = () -> LZ4Factory.fastestInstance().fastCompressor();

	public static final Supplier<LZ4Compressor> LZ4_HIGH = () -> LZ4Factory.fastestInstance().highCompressor();

	@Getter
	private final String storageFolder;

	// -------- snapshot settings ---------------
	// Snapshots LZ4 compressor
	// note: using LZ4 HIGH will require about twice more time
	@Getter
	private final Supplier<LZ4Compressor> snapshotLz4CompressorFactory;

	// -------- journal settings ---------------
	@Getter
	private final long journalFileMaxSize;
	@Getter
	private final int journalBufferSize;

	// use LZ4 compression if batch size (in bytes) exceeds this value for batches
	// threshold
	// average batch size depends on traffic and disk write delay and can reach up
	// to 20-100 kilobytes (3M TPS and 0.15ms disk write delay)
	// under moderate load for single messages compression is never used
	@Getter
	private final int journalBatchCompressThreshold;

	// Journals LZ4 compressor
	// note: using LZ4 HIGH is not recommended because of very high impact on
	// throughput
	@Getter
	private final Supplier<LZ4Compressor> journalLz4CompressorFactory;

	public static DiskSerializationProcessorConfiguration createDefaultConfig() {

		return DiskSerializationProcessorConfiguration.builder()

				.storageFolder(DEFAULT_FOLDER)

				.snapshotLz4CompressorFactory(LZ4_FAST)

				.journalFileMaxSize(4000 * ONE_MEGABYTE)

				.journalBufferSize(256 * 1024) // 256 KB - TODO calculate based on ringBufferSize

				.journalBatchCompressThreshold(2048)

				.journalLz4CompressorFactory(LZ4_FAST)

				.build();

	}
}