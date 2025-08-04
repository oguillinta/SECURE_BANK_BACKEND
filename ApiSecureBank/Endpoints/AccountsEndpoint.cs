using ApiSecureBank.DTOs;
using ApiSecureBank.Entities;
using ApiSecureBank.Repositories;
using AutoMapper;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.OutputCaching;

namespace ApiSecureBank.Endpoints
{
    public static class AccountsEndpoint
    {
        private readonly static string container = "accounts";
        public static RouteGroupBuilder MapAccounts(this RouteGroupBuilder group)
        {
            group.MapGet("/", GetAll)
                .CacheOutput(c => c.Expire(TimeSpan.FromSeconds(60)).Tag("accounts-get"));
            group.MapGet("/GetByNumber/{number}", GetByNumber)
                .CacheOutput(c => c.Expire(TimeSpan.FromSeconds(60)).Tag("accounts-get"));
            group.MapGet("/{id:int}", GetById);
            group.MapPost("/", Create);
            group.MapPut("/{id:int}", Update);
            group.MapDelete("/{id:int}", Delete);
            group.MapGet("/reports/summary", GetAccountsSummary)
                .CacheOutput(c => c.Expire(TimeSpan.FromSeconds(60)).Tag("accounts-get"));
            return group;
        }


        static async Task<Results<Ok<List<AccountDTO>>, NotFound>> GetAll(IAccountsRepository repository,
            ICustomersRepository customersRepository, IMapper mapper)
        {
            //if (!await customersRepository.Exist(customerId))
            //{
            //    return TypedResults.NotFound();
            //}
            var accounts = await repository.GetAll(1);
            var accountsDTO = mapper.Map<List<AccountDTO>>(accounts);
            return TypedResults.Ok(accountsDTO);
        }
        static async Task<Results<Ok<AccountDTO>, NotFound>> GetById(int id,
            IAccountsRepository accountsRepository, ICustomersRepository customersRepository,
            IMapper mapper)
        {
            //if (!await customersRepository.Exist(customerId))
            //{
            //    return TypedResults.NotFound();
            //}
            var account = await accountsRepository.GetById(id);
            if (account is null)
            {
                return TypedResults.NotFound();
            }
            var accountDTO = mapper.Map<AccountDTO>(account);
            return TypedResults.Ok(accountDTO);
        }
        static async Task<Ok<IEnumerable<AccountDTO>>> GetByNumber(string number, IAccountsRepository repository, IMapper mapper)
        {
            var accounts = await repository.GetByNumber(number);
            var accountsDTO = mapper.Map<IEnumerable<AccountDTO>>(accounts);
            return TypedResults.Ok(accountsDTO);
        }
        static async Task<Results<Created<AccountDTO>, NotFound>> Create(
            CreateAccountDTO createAccountDTO,
            IAccountsRepository repository,
            ICustomersRepository customersRepository,
            IOutputCacheStore outputCacheStore,
            IMapper mapper)
        {
            //if (!await customersRepository.Exist(customerId))
            //{
            //    return TypedResults.NotFound();
            //}
            var account = mapper.Map<Account>(createAccountDTO);
            //account.CustomerId = customerId;
            var id = await repository.Create(account);
            await outputCacheStore.EvictByTagAsync("accounts-get", default);
            var accountDTO = mapper.Map<AccountDTO>(account);
            return TypedResults.Created($"/typeAccounts/{id}", accountDTO);
        }

        static async Task<Results<NoContent, NotFound, BadRequest<string>>> Update( int id, CreateAccountDTO createAccountDTO,
            IAccountsRepository accountRepository, ICustomersRepository customersRepository, IOutputCacheStore outputCacheStore, IMapper mapper)
        {

            //if (!await customersRepository.Exist(customerId))
            //{
            //    return TypedResults.NotFound();
            //}
            if (!await accountRepository.Exist(id))
            {
                return TypedResults.NotFound();
            }
            var accountForUpdate = mapper.Map<Account>(createAccountDTO);
            accountForUpdate.Id = id;
            //accountForUpdate.CustomerId = customerId;

            await accountRepository.Update(accountForUpdate);
            await outputCacheStore.EvictByTagAsync("accounts-get", default);
            return TypedResults.NoContent();
        }

        static async Task<Results<NoContent, NotFound>> Delete(int id,
            IAccountsRepository accountRepository, ICustomersRepository customersRepository,
            IOutputCacheStore outputCacheStore)
        {
            //if (!await customersRepository.Exist(customerId))
            //{
            //    return TypedResults.NotFound();
            //}
            if (!await accountRepository.Exist(id))
            {
                return TypedResults.NotFound();
            }
            var account = await accountRepository.GetById(id);
            await accountRepository.Delete(id);
            await outputCacheStore.EvictByTagAsync("accounts-get", default);
            return TypedResults.NoContent();
        }

        static async Task<Ok<IEnumerable<AccountSummaryReportDTO>>> GetAccountsSummary(
            IAccountSummaryReportRepository accountSummaryReportRepository, IMapper mapper)
        {
            var accountSummaryReports = await accountSummaryReportRepository.GetAccountsSummary();
            var accountSummaryReportsDTO = mapper.Map<IEnumerable<AccountSummaryReportDTO>>(accountSummaryReports);
            return TypedResults.Ok(accountSummaryReportsDTO);
        }
    }
}
