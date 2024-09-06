package com.ncm.library.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.ncm.library.entity.User;
import com.ncm.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.Optional;

public class UserControllerTest {

  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void testGetAllUsers() throws Exception {
    User user1 = new User();
    User user2 = new User();
    when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/library/user/all"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0]").exists())
        .andExpect(jsonPath("$[1]").exists());

    verify(userService, times(1)).findAll();
  }

  @Test
  public void testCount() throws Exception {
    when(userService.count()).thenReturn(2L);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/library/user/count"))
        .andExpect(status().isOk())
        .andExpect(content().string("2"));

    verify(userService, times(1)).count();
  }

  // @Test
  // public void testGetOne() throws Exception {
  //   User user = new User();
  //   when(userService.existsById(1)).thenReturn(true);
  //   when(userService.findById(1)).thenReturn(Optional.of(user));

  //   mockMvc.perform(MockMvcRequestBuilders.get("/api/library/user/detail/1"))
  //       .andExpect(status().isOk())
  //       .andExpect(jsonPath("$.id").exists());

  //   verify(userService, times(1)).existsById(1);
  //   verify(userService, times(1)).findById(1);
  // }

  @Test
  public void testCreate() throws Exception {
    User user = new User();
    user.setName("John");
    user.setSurname("Doe");
    user.setEmail("john.doe@example.com");
    user.setMobile("1234567890");

    when(userService.existsByEmail(user.getEmail())).thenReturn(false);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/library/user/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"John\",\"surname\":\"Doe\",\"email\":\"john.doe@example.com\",\"mobile\":\"1234567890\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.message").value("usuario guardado"));

    verify(userService, times(1)).save(any(User.class));
  }

  @Test
  public void testUpdate() throws Exception {
    User user = new User();
    user.setIDuser(1);
    user.setName("John");
    user.setSurname("Doe");
    user.setEmail("john.doe@example.com");
    user.setMobile("1234567890");

    when(userService.existsById(1)).thenReturn(true);
    when(userService.existsByEmail(user.getEmail())).thenReturn(false);
    when(userService.findById(1)).thenReturn(Optional.of(user));

    mockMvc.perform(MockMvcRequestBuilders.put("/api/library/user/modify/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"John\",\"surname\":\"Doe\",\"email\":\"john.doe@example.com\",\"mobile\":\"1234567890\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.message").value("usuario actualizado"));

    verify(userService, times(1)).save(any(User.class));
  }

  @Test
  public void testDelete() throws Exception {
    when(userService.existsById(1)).thenReturn(true);

    mockMvc.perform(MockMvcRequestBuilders.delete("/api/library/user/delete/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("usuario eliminado"));

    verify(userService, times(1)).deleteById(1);
  }
}
