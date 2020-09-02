package rilesnyou.lab27.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movies {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

		private Long id;
		private String name;
		private String category;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return name;
		}
		public void setTitle(String name) {
			this.name = name;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		@Override
		public String toString() {
			return "Movies [id=" + id + ", name=" + name + ", category=" + category + "]";
		}
		
		
}
