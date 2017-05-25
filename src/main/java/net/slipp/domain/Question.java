package net.slipp.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private long id;

	private String title;

	private String contents;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;

	@OneToMany
	private List<Answer> answers;

	public long getId() {
		return id;
	}

	public Question setId(long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Question setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContents() {
		return contents;
	}

	public Question setContents(String contents) {
		this.contents = contents;
		return this;
	}

	public Question writeBy(User loginUser) {
		this.writer = loginUser;
		return this;
	}

	public Boolean isOwner(User writer) {
		return this.writer.getId() == writer.getId();
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
	}
}
