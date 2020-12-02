package com.kindustry.webservice;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.kindustry.vo.Person;

@Path("/restFulWebService")
// @Produces注释用来指定将要返回给client端的数据标识类型（MIME）。
// @Produces可以作为class注释，也可以作为方法注释，方法的@Produces注释将会覆盖class的注释。
// 覆盖的意思是假如方法声明了自己的Produce，那么以方法的为准，class的仅供参考
@Produces({ "application/json", "application/xml" })
public class RestFulWebService {

	public static List<Person> persons;
	static {
		persons = new LinkedList<Person>();
		Person person = new Person();
		person.setId("6272058");
		person.setName("刘德华");
		persons.add(person);
	}

	@GET
	@Path("/getPerson/{dd}")
	@Produces(MediaType.APPLICATION_XML)
	public Person getPerson(@PathParam("dd") String id) {

		if (id != null && id.length() > 0) {
			for (Person pp : persons) {
				if (id.equals(pp.getId())) {
					return pp;
				}
			}
			Person result = new Person();
			result.setId(id);
			return result;
		} else {
			return new Person();
		}
	}

	@POST
	@Path("/regPerson")
	// @Consumes与@Produces相反，用来指定可以接受client发送过来的MIME类型，
	// 同样可以用于class或者method，也可以指定多个MIME类型,一般用于@PUT，@POST。
	@Consumes({ "application/json", "application/xml" })
	public Response regPerson(Person person) {
		if (persons.contains(person)) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			persons.add(person);
			return Response.ok(person).build();
		}
	}

	@DELETE
	@Path("/delPerson")
	@Consumes({ "application/json", "application/xml" })
	public Response delPerson(@QueryParam("id") String id) {
		Person person = new Person();
		person.setId(id);
		if (persons.contains(person)) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			persons.remove(person);
			return Response.ok(person).build();
		}
	}

	@PUT
	@Path("/updatePerson")
	@Consumes({ "application/json", "application/xml" })
	public Response updatePerson(Person person) {
		if (persons.contains(person)) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			for (Person pp : persons) {
				if (pp.equals(person)) {
					pp.setName(person.getName());
				}
			}
			return Response.ok(person).build();
		}
	}

}
