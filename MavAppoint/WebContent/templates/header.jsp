<%@include file="top_header.jsp" %>
			<div>
				<ul class="nav navbar-nav">
				<li><a href="advising"><font style="color:#e67e22">Advising</font> </a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				
				<li><a href="register"><span class="glyphicon glyphicon-user"><font style="color:#e67e22">Register</font></a></li>
				<li><a href="login"><span class="glyphicon glyphicon-log-in"><font style="color:#e67e22">Login</font></a></li>
				<li><a class="dropdown-button hide-desktop" data-dropdown="dropdown-search" href="#">Search</a> 
					<form action="/search/" method="get">
					<fieldset><input id="top_search_field" name="q" placeholder="Search Mav Appointment" type="text"/> 
					<input type="submit" value="Submit"/></fieldset>
					</form>
					
					</li>
					<li><a href="instruction"><font style="color:#e67e22"> Help</font></a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>