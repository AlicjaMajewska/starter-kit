package pl.spring.demo.to;

public class BookTo {
    private Long id;
    private String title;
    private String authors;
    private long libraryId;
    private String libraryName;

    public BookTo() {
    }

    public BookTo(Long id, String title, String authors, long libraryId,
			String libraryName) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.libraryId = libraryId;
		this.libraryName = libraryName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

	public Long getLibraryId() {
		return new Long(libraryId);
	}

	public void setLibraryId(long libraryId) {
		this.libraryId = libraryId;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (libraryId ^ (libraryId >>> 32));
		result = prime * result
				+ ((libraryName == null) ? 0 : libraryName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookTo other = (BookTo) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libraryId != other.libraryId)
			return false;
		if (libraryName == null) {
			if (other.libraryName != null)
				return false;
		} else if (!libraryName.equals(other.libraryName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookTo [id=" + id + ", title=" + title + ", authors=" + authors
				+ ", libraryId=" + libraryId + ", libraryName=" + libraryName
				+ "]";
	}
	
	

	
}
