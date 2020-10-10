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
package org.camunda.bpm.engine.test.assertions.bpmn;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.helpers.Failure;
import org.camunda.bpm.engine.test.assertions.helpers.ProcessAssertTestCase;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * @author Dimitri Lavrenük (dimitri.lavr@gmail.com)
 */
public class ProcessInstanceAssertHasBusinessKeyTest extends ProcessAssertTestCase {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  @Test
  @Deployment(resources = {"bpmn/ProcessInstanceAssert-hasBusinessKey.bpmn"})
  public void testHasProcessDefinitionKey_Success_With_String() {
    // When
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(
      "ProcessInstanceAssert-hasBusinessKey",
      "aBusinessKey"
    );
    // Then
    assertThat(processInstance).hasBusinessKey("aBusinessKey");
  }

  @Test
  @Deployment(resources = {"bpmn/ProcessInstanceAssert-hasBusinessKey.bpmn"})
  public void testHasProcessDefinitionKey_Success_With_Null() {
    // When
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(
      "ProcessInstanceAssert-hasBusinessKey"
    );
    // Then
    assertThat(processInstance).hasBusinessKey(null);
  }

  @Test
  @Deployment(resources = {"bpmn/ProcessInstanceAssert-hasBusinessKey.bpmn"})
  public void testHasProcessDefinitionKey_Failure() {
    // Given
    final ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(
      "ProcessInstanceAssert-hasBusinessKey",
      "anotherBusinessKey"
    );
    // When
    runtimeService().suspendProcessInstanceById(processInstance.getId());
    // Then
    expect(new Failure() {
      @Override
      public void when() {
        assertThat(processInstance).hasProcessDefinitionKey("aBusinessKey");
      }
    });
  }

}
