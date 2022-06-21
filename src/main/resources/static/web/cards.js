
Vue.createApp({

    data() {
        return {
            clientes:[],
            idCliente:0,
            cliente1:[],
            cards:[],
            debitCards:[],
            creditCards:[],
            card1:[],
            anioTarjeta:'',
            mesTarjeta:'',
            anioActual:'',
            mesActual:'',
            fechaTarjeta:"",
            ultimos4Tarjeta:0,
        }
    },

    created() {
        axios.get('/api/clients')
            .then(datos => {
                this.clientes = datos.data
            })

        axios.get('/api/clients/1')
            .then(datos =>{
                this.cliente1 = datos.data
                this.cards = this.cliente1.cards
                this.card1 = this.cards[0]

    this.cards.forEach(card => card.cardType == 'DEBIT' && !card.disable ? this.debitCards.push(card) : card.cardType == 'CREDIT' && !card.disable ? this.creditCards.push(card):"");

            });

    },

    methods: {
    

        disableCard(id){
            axios.patch('/api/clients/current/cards',
            `id=${id}`,
            { headers: { "content-type": "application/x-www-form-urlencoded" } }
            )
                .then(function (response) {
                    console.log("Card deleted!!!");

                    window.location.href = "/web/cards.html";
                })
                        .catch(function (error) {
                        console.log(error);
                        });
        },



        thruDateTarjeta(fecha){
                    
            const thruDate = new Date (fecha)

            this.mesTarjeta = thruDate.getMonth()+1
            if(this.mesTarjeta <= 9)
            {
                this.mesTarjeta = "0" + this.mesTarjeta
            }


            this.anioTarjeta = thruDate.getFullYear()
            this.anioTarjeta = this.anioTarjeta.toString().slice(2)

            this.fechaTarjeta = this.mesTarjeta + "/"  + this.anioTarjeta
            return this.fechaTarjeta

        },


        numTarjeta(numero){
            let numeroTarjeta = numero.slice(0,4) + " " + numero.slice(4,8) + " " + numero.slice(8,12) + " " + numero.slice(12,16)
            return numeroTarjeta
        },

        expiredCard(thruDate)
        {
            // console.log(thruDate)

            let fecha = new Date(thruDate).getMonth()
            let hoy = new Date().getMonth()
            let guatita='';

            // console.log(fecha)
            // console.log(hoy)

            if(fecha > hoy)
            {
                guatita = 'triste'
            }
            else{
                guatita='feliz'
            }

            // console.log(guatita)
        },

        prueba(cvv)
        {
            return console.log(cvv)
        }

    },
    
    computed: {


},


}).mount('#app')
