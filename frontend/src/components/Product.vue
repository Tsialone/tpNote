<script>
import Rating from './Rating.vue';


export default {

    props: {
        data: {
            typeof: Object,
            require: true,
        },
        cheapestPrice: {
            typeof: Number,
            default: 0
        },
        cheapestPriceColor: {
            typeof:String ,
            default:'bg-green-500'
        }
    },
    components : {
        Rating
    }
}

</script>

<template>
    <div>
        <h3>
            {{ name }}
        </h3>
        <slot>
            
        </slot>
        <div :class="data.unit_price === cheapestPrice ? cheapestPriceColor : 'bg-white'">
            <p>
                <b>Prix unitaire: </b> {{ data.unit_price }} € <br>
                <b>Quantié: </b> {{ data.quantity }} en stock <br>
            </p>
        </div>
        <div>
            Description: <span v-html="data.description"></span>
        </div>
        <div>
            <Rating :nbr-etoile="data.note"  :max="5">

            </Rating>
        </div>
        <hr>
    </div>
</template>


<style scoped>
.bg-green-500 {
    background-color: green;
}
.bg-red-500 {
    background-color: red;
}
</style>