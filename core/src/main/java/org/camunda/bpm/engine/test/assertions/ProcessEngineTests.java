/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
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
package org.camunda.bpm.engine.test.assertions;


import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.engine.test.assertions.cmmn.CmmnAwareTests;

/**
 * Convenience class to access all available Camunda Platform related 
 * Assertions PLUS helper methods. Use it with a static import:
 *
 * import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
 *   
 * @see BpmnAwareTests if you only want to see BPMN related functionality
 *
 * @author Martin Schimak (martin.schimak@plexiti.com)
 * @author Martin Günther (martin.guenter@holisticon.de)
 * @author Malte Sörensen (malte.soerensen@holisticon.de)
 */
public class ProcessEngineTests extends CmmnAwareTests {

  protected ProcessEngineTests() {}

}
