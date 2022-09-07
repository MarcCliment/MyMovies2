package com.example.gfttraining.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain=true)
@Entity
@Table(name="user_movie")
public class UserMovie {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	String username;
	String movie;
	Boolean favorite;
	Integer personal_rating;
	String notes;
}
