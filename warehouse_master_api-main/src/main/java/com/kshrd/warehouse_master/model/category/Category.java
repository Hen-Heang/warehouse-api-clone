package com.kshrd.warehouse_master.model.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
   private Integer id;
   private String name;
   private String createdDate;
   private String updatedDate;
}
