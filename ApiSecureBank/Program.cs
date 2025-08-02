using ApiSecureBank.DBContext;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);


// Services configuration START

//Conexion a la base de datos
builder.Services.AddDbContext<ApplicationDBContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));


// Services configuration END

var app = builder.Build();

app.MapGet("/", () => "Hello World!");

// Middleware configuration START


// Middleware configuration END

app.Run();
