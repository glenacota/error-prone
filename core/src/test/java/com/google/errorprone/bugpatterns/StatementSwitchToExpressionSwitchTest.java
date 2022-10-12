/*
 * Copyright 2022 The Error Prone Authors.
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

package com.google.errorprone.bugpatterns;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for {@link StatementSwitchToExpressionSwitch}. */
@RunWith(JUnit4.class)
public final class StatementSwitchToExpressionSwitchTest {
  private final CompilationTestHelper helper =
      CompilationTestHelper.newInstance(StatementSwitchToExpressionSwitch.class, getClass());

  @Test
  public void switchByEnum_error() {
    helper
        .addSourceLines(
            "Test.java",
            "class Test {",
            "  enum Side {OBVERSE, REVERSE};",
            "  public Test(int foo) {",
            "  }",
            " ",
            "  public void foo(Side side) { ",
            "    // BUG: Diagnostic contains: [StatementSwitchToExpressionSwitch]",
            "    switch(side) {",
            "       case OBVERSE:",
            "          System.out.println(\"obverse\");",
            "          break;",
            "       case REVERSE:",
            "          System.out.println(\"reverse\");",
            "    }",
            "  }",
            "}")
        .doTest();
  }

  @Test
  public void dynamicWithThrowableDuringInitializationFromMethod_noMatch() {

    helper
        .addSourceLines(
            "Test.java",
            "class Test {",
            "  Throwable foo = bar(); ",
            "  public Test(int foo) {",
            "  } ",
            " ",
            "  private static Throwable bar() { ",
            "    return new NullPointerException(\"initialized with return value\"); ",
            "  } ",
            "}")
        .doTest();
  }
}
