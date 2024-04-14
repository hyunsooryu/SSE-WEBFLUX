package me.burnie.ssewebflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleEvent {
   private String name;
   private String message;
   private String question;
   private List<String> answers = List.of("1","2","3");
}
