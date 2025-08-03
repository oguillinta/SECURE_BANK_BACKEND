using ApiSecureBank.DBContext;
using ApiSecureBank.Endpoints;
using ApiSecureBank.Repositories;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Logging;

var builder = WebApplication.CreateBuilder(args);


// Services configuration START

// Habilitar logging detallado de Identity Model (solo en desarrollo)

if (builder.Environment.IsDevelopment())
{
    IdentityModelEventSource.ShowPII = true;
}

builder.Services.AddDistributedMemoryCache();

//Conexion a la base de datos temporal
builder.Services.AddDbContext<ApplicationDBContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));

// Se añade CORS para permitir el acceso desde el frontend
builder.Services.AddCors(options =>
{
    options.AddDefaultPolicy(configuration =>
    {
        configuration.WithOrigins(builder.Configuration["allowedOrigins"]!).AllowAnyMethod().AllowAnyHeader();
    });

    options.AddPolicy("free", configuration =>
    {
        configuration.AllowAnyOrigin().AllowAnyHeader().AllowAnyMethod();
    });
});

//Servicio de cache manual
builder.Services.AddOutputCache();

//Permito a los endpoints emitir metadata
builder.Services.AddEndpointsApiExplorer();
//Anade el servicio Swagger
builder.Services.AddSwaggerGen();

//Inyeccion de dependencias
builder.Services.AddScoped<IAccountsRepository, AccountsRepository>();
builder.Services.AddScoped<ICustomersRepository, CustomersRepository>();
builder.Services.AddScoped<IInterestRatesRepository, InterestRatesRepository>();
builder.Services.AddHttpContextAccessor();

// Inyeccion para automapper Entities vs DTOs:
builder.Services.AddAutoMapper(cfg =>
{
    cfg.AddMaps(typeof(Program).Assembly); // Add maps from the assembly
});
// Services configuration END

var app = builder.Build();

// Middleware configuration START

if (builder.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.MapGet("/", () => "Hello World!");
app.UseCors();
app.MapGroup("/Accounts").MapAccounts();
app.MapGroup("/Customers").MapCustomers();


// Middleware configuration END

app.Run();
