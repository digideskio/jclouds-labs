/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.vcloud.director.v1_5.features;

import java.util.concurrent.TimeUnit;

import org.jclouds.concurrent.Timeout;
import org.jclouds.vcloud.director.v1_5.domain.ReferenceType;
import org.jclouds.vcloud.director.v1_5.domain.Task;
import org.jclouds.vcloud.director.v1_5.domain.TasksList;

/**
 * Provides synchronous access to {@link Task} objects.
 * 
 * @see TaskAsyncClient
 * @author grkvlt@apache.org
 */
@Timeout(duration = 180, timeUnit = TimeUnit.SECONDS)
public interface TaskClient {

   /**
    * Retrieves a list of tasks.
    * 
    * @param orgId the unique id for the organization
    * @return a list of tasks
    */
   TasksList getTaskList(String orgId);

   /**
    * Retrieves a task.
    * 
    * @return the task or null if not found
    */
   Task getTask(String taskId);

   Task getTask(ReferenceType<?> taskRef);

   /**
    * Cancels a task.
    */
   void cancelTask(String taskId);

   void cancelTask(ReferenceType<?> taskRef);
}