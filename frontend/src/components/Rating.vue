<script>
export default {
    name: "Rating",
    props: {
        nbrEtoile: {
            type: Number,
            default: 0
        },
        max: {
            validator (value ){
                return value == 5;
            },
            type: Number,
            default: 5
        }
    },
    computed: {
         numberOfPlainItem (){
            return this.nbrEtoile  * 100 % 100 === 0 ? this.nbrEtoile: Math.floor(this.nbrEtoile);

        },
        numberOfHalfEmptyItem (){
            // console.error (this.max -  Math.floor (this.nbrEtoile));
            return this.max -  Math.floor (this.nbrEtoile);
        },
        numberOfHalfHalfItem   (){
            return this.nbrEtoile  * 100 % 100 !== 0 ? 1:0;
        }
    }


}
</script>

<template>
    <div class="etoiles">
        <i class="fa-solid fa-star" v-for="n in numberOfPlainItem" :key="n"> </i>
        <i class="fa-solid fa-star-half-stroke" v-for="n in numberOfHalfHalfItem" :key="n"> </i>
        <i class="fa-regular fa-star" v-for="n in numberOfHalfEmptyItem" :key="n"> </i>

    </div>
</template>

<style scoped>
.etoiles {
    display: flex;
}
</style>
