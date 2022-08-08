

Vue.createApp({
    data() {
    return {
        clientes: [],
        ownAccount:'',
        sourceAccount:[],
        destinationAccount:[],
        amount:0,
        description:'',
        idCliente: 0,
        cliente1: [],
        accounts:[],
        };
    },

    created() {
    axios.get("/api/clients").then((datos) => {
    this.clientes = datos.data;
    });

    axios.get("/api/clients/current")
    .then((datos) => {
      this.cliente1 = datos.data;
      console.log(this.cliente1);
      this.accounts = this.cliente1.accounts.filter(account => account.disable == false);
    });
  },

  methods: {
    createTransfer() {
      axios
        .post(
          "/api/transactions",
          `sourceAccountNumber=${this.sourceAccount}&destinationAccountNumber=${this.destinationAccount}&amount=${this.amount}&description=${this.description}`,
          { headers: { "content-type": "application/x-www-form-urlencoded" } }
        )
        .then(function (response) {
          console.log("Transaction done!!!");

          window.location.href = "/web/accounts.html";
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },

  computed: {},
}).mount("#app");