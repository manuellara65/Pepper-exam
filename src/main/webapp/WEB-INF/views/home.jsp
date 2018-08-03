<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="org.webjars.AssetLocator" %>
<html>
<head>
	<title>Home</title>
	<link href="<c:url value='<%= AssetLocator.getWebJarPath("css/bootstrap.min.css") %>'/>" rel="stylesheet"/>
	<script type="text/javascript" src="<c:url value='<%=AssetLocator.getWebJarPath("jquery/1.11.1/jquery.min.js") %>'/>"></script>
	<script>
		$(document).ready(function(){
			
			loadCustomers();
			
			$("#submit").click(function(event){
				event.preventDefault();
				$.ajax({
					url:'http://localhost:9000/exam/customer/add',
					data:$("form").serialize(),
					type:'GET',
					contentType:'application/json',
					error:function(error){
						alert("error:"+error);
					},
					success:function(response){
						loadCustomers();
						clear();
					}
				});
			});
			
			$("#cancel").click(function(){
				clear();
			});
			
		});
		
		
		
	</script>
	
	<script>
		function deleteCustomer(idCustomer){	
			$.ajax({
				type:'GET',
				url:'http://localhost:9000/exam/customer/remove',
				data:"id_customer="+idCustomer,
				dataType:'json',
				contentType:'application/json',
				error:function(error){
					alert("error while deleting record.");
				},
				success:function(response){
					if(response.statuscode == 1){
						alert("successfully deleted.");
					}else{
						alert("Problem while deleting record.");
					}
				},
				complete:function(data){
					loadCustomers();
				}
				
			});
		}
		
		function getCustomerInfo(idCustomer){	
			
			$.ajax({
				type:'GET',
				url:'http://localhost:9000/exam/customer/get-customer-info',
				data:"id_customer="+idCustomer,
				dataType:'json',
				contentType:'application/json',
				error:function(error){
					alert("error:"+error);
				},
				success:function(response){
					$("#firstname").val(response.firstname);
					$("#lastname").val(response.lastname);
					$("#address").val(response.address);
					$("#id_customer").val(response.id_customer);
				},
				complete:function(data){
				}
				
			});
		}
		
		function loadCustomers(){
			$.ajax({
				   url: 'http://localhost:9000/exam/customer/show-all',
				   type: 'POST',
				   dataType:'json',
				   error: function() {
				      alert("error");
				   },
				   success: function(data) {
					   
					   $("#tblConsumer > tbody").html("");
					
					   $.each(data,function(index,element){
						   $("#tblConsumer").append("<tr>"
						   							+"<td>"+element.firstname+"</td>"
						   							+"<td>"+element.lastname+"</td>"
						   							+"<td>"+element.address+"</td>"
						   							+"<td> <label onclick='deleteCustomer("+element.id_customer+")'>Delete</label> | <label onclick='getCustomerInfo(" + element.id_customer + ")'>update</label></td>"
						   							+"</tr>");
					   })
				   }
			});
		}
		
		function clear(){
			$("#firstname").val("");
			$("#lastname").val("");
			$("#address").val("");
			$("#id_customer").val(0);
		}
	</script>
</head>
<body>
<div class="container">

	<form id="addCustomer">
		<div class="form-group">
			<label for="firstname">Firstname</label>
			<input type="text" class="form-control" id="firstname" name="firstname" required>
		</div>
		<div class="form-group">
			<label for="lastname">Lastname</label>
			<input type="text" class="form-control" id="lastname" name="lastname" required>
		</div>
		<div class="form-group">
			<label for="address">Address</label>
			<input type="text" class="form-control" id="address" name="address" required>
		</div>
		<input type="hidden" class="id_customer" name="id_customer" id="id_customer" value="0"/>
		
		<button class="btn btn-success" id="submit">Submit</button>
	</form>
	
	<button class="btn btn-success" id="cancel">Cancel Update</button>
		
	<h1>
		List of Customers
	</h1>

	<table class="table" id='tblConsumer'>
		<thead>
			<tr>
				<th>Lastname</th>
				<th>Firstname</th>
				<th>Address</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>


</body>
</html>
