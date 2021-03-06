/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicecomb.company.worker;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.seanyinx.github.unit.scaffolding.Randomness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(FibonacciRestEndpoint.class)
@ActiveProfiles("dev")
public class FibonacciRestEndpointTest {
  private final long expected = Randomness.nextLong();
  private final int term = Randomness.nextInt();

  @MockBean
  private FibonacciService fibonacciService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void returnsTokenOfAuthenticatedUser() throws Exception {
    when(fibonacciService.term(term)).thenReturn(expected);

    mockMvc.perform(
        get("/fibonacci/term")
            .param("n", String.valueOf(term)))
        .andExpect(status().isOk())
        .andExpect(content().string(String.valueOf(expected)));
  }
}