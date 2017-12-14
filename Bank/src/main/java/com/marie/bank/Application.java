package com.marie.bank;


public class Application  {

//    @Autowired
//    private OperationService operationService;
//
//    @Autowired
//    private AccountService accountService;
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//        createData();
//    }
//
//    @Transactional
//    private void createData() {
//
//        try {
//
//            //create accounts
//            Account jackAccount = new Account(new Client("Jack", "Bauer"));
//            Account kimAccount = new Account(new Client("Chloe", "O'Brian"));
//            Account chloeAccount = new Account(new Client("Kim", "Bauer"));
//            Account davidAccount = new Account(new Client("David", "Palmer"));
//            Account michelleAccount = new Account(new Client("Michelle", "Dessler"));
//            accountService.createAccount(jackAccount);
//            accountService.createAccount(kimAccount);
//            accountService.createAccount(chloeAccount);
//            accountService.createAccount(davidAccount);
//            accountService.createAccount(michelleAccount);
//            //create operations
//            for (int i = 1; i < 6; i++) {
//                operationService.depositOperation(i, 100*i);
//            }
//            for (int i = 1; i < 6; i++) {
//                operationService.withdrawalOperation(i, 50*i);
//            }
//
//        } catch (NegativeAmountException | AmountGreaterThanBalanceException ex) {
//            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
