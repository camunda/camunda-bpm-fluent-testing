package org.camunda.bpm.engine.test.assertions;

import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.assertions.cmmn.MilestoneHolder;
import org.camunda.bpm.engine.test.assertions.cmmn.StageHolder;
import org.camunda.bpm.engine.test.assertions.cmmn.TaskHolder;

import java.util.Map;

/**
 * Class meant to statically access all
 * camunda BPM Process Engine Assertions.
 * <p/>
 * In your code use import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.*;
 *
 * @author Martin Schimak <martin.schimak@plexiti.com>
 * @author Martin Günther <martin.guenter@holisticon.de>
 * @author Malte Sörensen <malte.soerensen@holisticon.de>
 */
public class ProcessEngineAssertions extends Assertions {

  static ThreadLocal<ProcessEngine> processEngine = new ThreadLocal<ProcessEngine>();

  protected ProcessEngineAssertions() {
  }

  /**
   * Resets operations done via calling init(ProcessEngine processEngine)
   * to its clean state - just as before calling init() for the first time.
   */
  public static void reset() {
    ProcessEngineAssertions.processEngine.remove();
    AbstractProcessAssert.resetLastAsserts();
  }

  /**
   * Assert that... the given ProcessDefinition meets your expectations.
   *
   * @param   actual ProcessDefinition under test
   * @return Assert object offering ProcessDefinition specific assertions.
   */
  public static ProcessDefinitionAssert assertThat(final ProcessDefinition actual) {
    return ProcessDefinitionAssert.assertThat(processEngine(), actual);
  }

  /**
   * Retrieve the processEngine bound to the current testing thread
   * via calling init(ProcessEngine processEngine). In case no such
   * processEngine is bound yet, init(processEngine) is called with
   * a default process engine.
   *
   * @return processEngine bound to the current testing thread
   * @throws IllegalStateException in case a processEngine has not
   *          been initialised yet and cannot be initialised with a
   *          default engine.
   */
  public static ProcessEngine processEngine() {
    ProcessEngine processEngine = ProcessEngineAssertions.processEngine.get();
    if (processEngine != null)
      return processEngine;
    Map<String, ProcessEngine> processEngines = ProcessEngines.getProcessEngines();
    if (processEngines.size() == 1) {
      processEngine = processEngines.values().iterator().next();
      init(processEngine);
      return processEngine;
    }
    String message = processEngines.size() == 0 ? "No ProcessEngine found to be " +
      "registered with " + ProcessEngines.class.getSimpleName() + "!"
      : String.format(processEngines.size() + " ProcessEngines initialized. Call %s.init" +
      "(ProcessEngine processEngine) first!", ProcessEngineAssertions.class.getSimpleName());
    throw new IllegalStateException(message);
  }

  /**
   * Bind an instance of ProcessEngine to the current testing calls done
   * in your test method.
   *
   * @param   processEngine ProcessEngine which should be bound to the
   *          current testing thread.
   */
  public static void init(final ProcessEngine processEngine) {
    ProcessEngineAssertions.processEngine.set(processEngine);
    AbstractProcessAssert.resetLastAsserts();
  }

  /**
   * Assert that... the given ProcessInstance meets your expectations.
   *
   * @param   actual ProcessInstance under test
   * @return  Assert object offering ProcessInstance specific assertions.
   */
  public static ProcessInstanceAssert assertThat(final ProcessInstance actual) {
    return ProcessInstanceAssert.assertThat(processEngine(), actual);
  }

  /**
   * Assert that... the given Task meets your expectations.
   *
   * @param   actual Task under test
   * @return  Assert object offering Task specific assertions.
   */
  public static TaskAssert assertThat(final Task actual) {
    return TaskAssert.assertThat(processEngine(), actual);
  }

  /**
   * Assert that... the given Job meets your expectations.
   *
   * @param   actual Job under test
   * @return  Assert object offering Job specific assertions.
   */
  public static JobAssert assertThat(final Job actual) {
    return JobAssert.assertThat(processEngine(), actual);
  }

  /**
   * Start making assertions on a task
   * @param taskHolder the task assertions are to be made about
   * @return TaskAssert object for given task
   */
  public static org.camunda.bpm.engine.test.assertions.cmmn.TaskAssert assertThat(TaskHolder taskHolder) {
    return new org.camunda.bpm.engine.test.assertions.cmmn.TaskAssert(processEngine(), taskHolder);
  }

  /**
   * Start making assertions on a stage
   *
   * @param stageHolder
   * 		the stage assertions are to be made about
   * @return StageAssert object for given stage
   */
  public static org.camunda.bpm.engine.test.assertions.cmmn.StageAssert assertThat(StageHolder stageHolder) {
    return new org.camunda.bpm.engine.test.assertions.cmmn.StageAssert(processEngine(), stageHolder);
  }

  /**
   * Start making assertions on a milestone
   *
   * @param milestoneHolder
   * 		the milestone assertions are to be made about
   * @return StageAssert object for given stage
   */
  public static org.camunda.bpm.engine.test.assertions.cmmn.MilestoneAssert assertThat(MilestoneHolder milestoneHolder) {
    return new org.camunda.bpm.engine.test.assertions.cmmn.MilestoneAssert(processEngine(), milestoneHolder);
  }

  /**
   * Assert that... the given CaseDefinition meets your expectations.
   *
   * @param   actual ProcessDefinition under test
   * @return  Assert object offering ProcessDefinition specific assertions.
   */
  public static CaseDefinitionAssert assertThat(final CaseDefinition actual) {
    return CaseDefinitionAssert.assertThat(processEngine(), actual);
  }
}
