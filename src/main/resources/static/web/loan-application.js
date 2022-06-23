var swiper = new Swiper('.blog-slider', {
  spaceBetween: 30,
  effect: 'fade',
  loop: true,
  mousewheel: {
    invert: false,
  },
  // autoHeight: true,
  pagination: {
    el: '.blog-slider__pagination',
    clickable: true,
  }
});


Vue.createApp({
  data() {
    return {
      clientes: [],
      loans:[],
      mortgageLoan:[],
      autoLoan:[],
      personalLoan:[],
      loanId:"",
      loanType:0,
      loanPayments:0,
      loanAmount: 0,
      loanAccount: "",
      idCliente: 0,
      payments:[],
      mortgagePayments:[],
      autoPayments:[],
      personalLoan:[],
      cliente1: [],
      accounts: [],
    };
  },

  created() {
    axios.get("http://localhost:8585/api/clients")
        .then((datos) => {
      this.clientes = datos.data;
    });

    axios.get("http://localhost:8585/api/clients/current")
        .then((datos) => {
      this.cliente1 = datos.data;
      this.accounts = this.cliente1.accounts;
        });

    axios.get("http://localhost:8585/api/loans")
          .then((datos)=>{
            this.loans = datos.data;
            console.log(this.loans);
            
            this.mortgageLoan = this.loans.filter(loan => loan.id==1);
            this.personalLoan = this.loans.filter(loan => loan.id==2);
            this.autoLoan = this.loans.filter(loan => loan.id==3);
            
            console.log(this.mortgageLoan);
            console.log(this.mortgageLoan[0].payments);

            this.mortgagePayments = this.mortgageLoan[0].payments;
            console.log(this.mortgagePayments);

            this.autoPayments = this.autoLoan[0].payments;
            this.personalPayments = this.personalLoan[0].payments;

              // this.payments.push(loan.payments);
              // console.log(this.payments);
            
          })
  },

  methods: {
    createLoan() {
      axios.post("http://localhost:8585/api/loans",
          {
            idLoan:this.loanType,
            amount:this.loanAmount,
            payments:this.loanPayments,
            accountNumber:this.loanAccount,
          }
        )
        .then(function (response) {
          console.log("Loan aproved!!!");
          window.location.href = "http://localhost:8585/web/accounts.html";
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    payments(loanType)
    {
      if(loanType == 1)
      {
        return this.loanPayments = this.mortgagePayments;
      }
      else if(loanType == 2)
      {
        return this.loanPayments = this.personalPayments;
      }
      else
      {
        return this.loanPayments = this.autoPayments;
      }
    },

    funcionPrueba(){
  
      if(this.loanType == 1)
      {
        this.payments = this.mortgagePayments;
      }
      else if(this.loanType == 2)
      {
        this.payments = this.personalPayments;
      }
      else
      {
        this.payments = this.autoPayments;
      }

    }



  },

  computed: {},
}).mount("#app");
