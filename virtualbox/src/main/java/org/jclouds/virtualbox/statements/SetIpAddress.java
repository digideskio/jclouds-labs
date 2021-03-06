/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.virtualbox.statements;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jclouds.scriptbuilder.domain.OsFamily;
import org.jclouds.scriptbuilder.domain.Statement;
import org.jclouds.virtualbox.domain.NetworkInterfaceCard;

import com.google.common.collect.ImmutableList;

/**
 * Sets the ipaddress using ssh. Used for host-only networking
 * 
 * @author dralves
 * 
 */
public class SetIpAddress implements Statement {

   private String script;

   public SetIpAddress(NetworkInterfaceCard networkInterfaceCard) {
      String ipAddress = networkInterfaceCard.getNetworkAdapter().getStaticIp();
      checkNotNull(ipAddress, "ip address");
      int slot = (int) networkInterfaceCard.getSlot();
      String iface = null;
      switch (slot) {
         case 0:
            iface = "eth0";
            break;
         case 1:
            iface = "eth1";
            break;
         case 2:
            iface = "eth2";
            break;
         case 3:
            iface = "eth3";
            break;
         default:
            throw new IllegalArgumentException("slot must be 0,1,2,3 (was: " + slot + ")");
      }
      script = String.format("ifconfig %s %s;", iface, ipAddress);
   }

   @Override
   public Iterable<String> functionDependencies(OsFamily family) {
      return ImmutableList.of();
   }

   @Override
   public String render(OsFamily family) {
      if (checkNotNull(family, "family") == OsFamily.WINDOWS)
         throw new UnsupportedOperationException("windows not yet implemented");
      return script;
   }

}
