

Vue.createApp({
    data() {
    return {
        clientes: [],
        idCliente: 0,
        cliente1: [],
        accounts: [],
        loans: [],
        cards: [],
        cardType: "",
        cardColor: "",
        cardHolder: "",
        };
    },

    created() {
    axios.get("/api/clients").then((datos) => {
    this.clientes = datos.data;
    });

    axios.get("/api/clients/current").then((datos) => {
      this.cliente1 = datos.data;
      console.log(this.cliente1);
      this.cards = this.cliente1.cards;
    });
  },

  methods: {
    createCard() {
      axios
        .post(
          "/api/clients/current/cards",
          `cardColor=${this.cardColor}&cardType=${this.cardType}`,
          { headers: { "content-type": "application/x-www-form-urlencoded" } }
        )
        .then(function (response) {
          console.log("Card created!!!");

          window.location.href = "http://localhost:8585/web/cards.html";
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },

  computed: {},
}).mount("#app");
