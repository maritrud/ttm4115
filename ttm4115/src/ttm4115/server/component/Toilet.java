package ttm4115.server.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Toilet {
	int id;
	int status;
	String description;
	String time;
	static DateFormat df = new SimpleDateFormat("dd/MM/yy-HH:mm:ss");
	
	public Toilet() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int isStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime() {
		Date dateobj = new Date();
		this.time = df.format(dateobj);
	}
	
}
