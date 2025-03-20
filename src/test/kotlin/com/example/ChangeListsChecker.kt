package com.example.tests
import com.intellij.driver.sdk.step
import com.intellij.driver.sdk.ui.components.UiComponent.Companion.waitFound
import com.intellij.driver.sdk.ui.components.common.ideFrame
import com.intellij.driver.sdk.ui.components.elements.button
import com.intellij.driver.sdk.ui.components.elements.checkBox
import com.intellij.driver.sdk.ui.components.elements.tree
import com.intellij.driver.sdk.ui.components.settings.settingsDialog
import com.intellij.driver.sdk.wait
import com.intellij.driver.sdk.waitForIndicators
import com.intellij.ide.starter.driver.engine.runIdeWithDriver
import com.intellij.ide.starter.examples.data.TestCases
import com.intellij.ide.starter.junit5.hyphenateWithClass
import com.intellij.ide.starter.runner.CurrentTestMethod
import com.intellij.ide.starter.runner.Starter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class ChangeListsChecker {

    @Test
    fun testAutoCreateChangelists() {
        val testContext = Starter
            .newContext(CurrentTestMethod.hyphenateWithClass(), TestCases.IC.GradleQuantumSimple)
            .prepareProjectCleanImport()


        testContext.runIdeWithDriver().useDriverAndCloseIde {
            ideFrame {
                println(">> Waiting for IDE loading...")
                waitForIndicators(5.minutes)
                //toolbarHeader.menuAndToolbarsSettings {
                step("Open Settings") {
                    openSettingsDialog()
                    wait(2.seconds)
                }
                settingsDialog {
                    step("Open Changelists") {
                        step("Open 'Version Control'") {
                            println(">> Unfolding 'Version Control'")
                            val settingsTree = tree().waitFound(timeout = 5.seconds)
                            settingsTree.expandPath("Version Control")
                            wait(2.seconds)
                        }
                        step("Click on 'Changelists'") {
                            println(">> Clicking on 'Changelists'")
                            waitOneContainsText("Changelists").click()
                            wait(2.seconds)
                        }
                    }
                    step("find and turn on 'Create changelists automatically'") {
                        println(">> Checking 'Create changelists automatically'")
                        val checkbox = this.checkBox("//div[@text='Create changelists automatically']")
                        checkbox.check()
                        Assertions.assertTrue(checkbox.isSelected())
                        println("Checkbox: ${checkbox.isSelected()}")
                    }
                    step("Close Settings dialog") {
                        wait(2.seconds)
                        this.button("OK").waitFound(5.seconds).click()
                    }
                }
            }
        }
    }
}