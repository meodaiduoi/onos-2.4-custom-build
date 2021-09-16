/*
 * Copyright 2016-present Open Networking Foundation
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
 */
package org.onosproject.pcepio.types;

import java.util.Objects;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.pcepio.protocol.PcepVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;

/**
 * Provide the Maximum Link Bandwidth.
 */
public class MaximumLinkBandwidthSubTlv implements PcepValueType {

    /* Reference :[RFC5305]/3.3.
     * 0                   1                   2                   3
      0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |              Type=[TDB34]      |             Length=4         |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |               Maximum  Link Bandwidth                         |
     +-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-++-+-+-+-+-+-+-+-+-++-+-+-+-
     */

    private static final Logger log = LoggerFactory.getLogger(MaximumLinkBandwidthSubTlv.class);

    public static final short TYPE = 23;
    public static final short LENGTH = 4;

    private final int rawValue;

    /**
     * Constructor to initialize rawValue.
     *
     * @param rawValue Maximum-Link-Bandwidth
     */

    public MaximumLinkBandwidthSubTlv(int rawValue) {
        this.rawValue = rawValue;
    }

    /**
     * Returns newly created MaximumLinkBandwidthTlv object.
     *
     * @param raw value of Maximum-Link-Bandwidth
     * @return object of MaximumLinkBandwidthTlv
     */
    public static MaximumLinkBandwidthSubTlv of(final int raw) {
        return new MaximumLinkBandwidthSubTlv(raw);
    }

    /**
     * Returns value of Maximum  Link Bandwidth.
     *
     * @return rawValue Maximum  Link Bandwidth
     */
    public int getInt() {
        return rawValue;
    }

    @Override
    public PcepVersion getVersion() {
        return PcepVersion.PCEP_1;
    }

    @Override
    public short getType() {
        return TYPE;
    }

    @Override
    public short getLength() {
        return LENGTH;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MaximumLinkBandwidthSubTlv) {
            MaximumLinkBandwidthSubTlv other = (MaximumLinkBandwidthSubTlv) obj;
            return Objects.equals(rawValue, other.rawValue);
        }
        return false;
    }

    @Override
    public int write(ChannelBuffer c) {
        int iLenStartIndex = c.writerIndex();
        c.writeShort(TYPE);
        c.writeShort(LENGTH);
        c.writeInt(rawValue);
        return c.writerIndex() - iLenStartIndex;
    }

    /**
     * Reads the channel buffer and returns object of MaximumLinkBandwidthTlv.
     *
     * @param c input channel buffer
     * @return object of MaximumLinkBandwidthTlv
     */
    public static MaximumLinkBandwidthSubTlv read(ChannelBuffer c) {
        return MaximumLinkBandwidthSubTlv.of(c.readInt());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .add("Type", TYPE)
                .add("Length", LENGTH)
                .add("Value", rawValue)
                .toString();
    }
}
