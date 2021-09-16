/*
 * Copyright 2018-present Open Networking Foundation
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

package org.onosproject.segmentrouting.mcast;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.onlab.packet.VlanId;
import org.onosproject.net.ConnectPoint;

/**
 * Custom serializer for {@link McastFilteringObjStoreKey}.
 */
class McastFilteringObjStoreSerializer extends Serializer<McastFilteringObjStoreKey> {

    /**
     * Creates {@link McastFilteringObjStoreSerializer} serializer instance.
     */
    McastFilteringObjStoreSerializer() {
        // non-null, immutable
        super(false, true);
    }

    @Override
    public void write(Kryo kryo, Output output, McastFilteringObjStoreKey object) {
        kryo.writeClassAndObject(output, object.ingressCP());
        kryo.writeClassAndObject(output, object.vlanId());
        kryo.writeClassAndObject(output, object.isIpv4());
    }

    @Override
    public McastFilteringObjStoreKey read(Kryo kryo, Input input, Class<McastFilteringObjStoreKey> type) {
        ConnectPoint ingressCP = (ConnectPoint) kryo.readClassAndObject(input);
        VlanId vlanId = (VlanId) kryo.readClassAndObject(input);
        boolean isIpv4 = (boolean) kryo.readClassAndObject(input);
        return new McastFilteringObjStoreKey(ingressCP, vlanId, isIpv4);
    }
}
