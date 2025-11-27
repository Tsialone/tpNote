<template>
    <h1>Foramtion vue js - <small>{{ company_name }}</small> </h1>
    <!-- <h2>La poprs {{ props }}</h2> -->

    <ul>
        <li    :class="{'maxDuration': isMaxDuration (topic)}" v-for="(topic, index) in duractionSortedByDurationDesc" :key="index">
            
            {{ topic.name }}
            -
            <b> {{ topic.duraction }} mn</b>
        </li>
    </ul>
</template>
<script>
export default {
    data() {
        return {
            company_name: "Company group",
            topics: [
                { name: 'théorie', duraction: 15 },
                { name: 'introduction', duraction: 5 },
                { name: 'pratique', duraction: 65 },
            ]
        }
    },
    computed: {
        duractionSortedByDurationDesc() {
            return this.topics.sort((a, b) => -a.duraction + b.duraction);
        }
    },
    methods:{
        isMaxDuration (topic) {
            return topic.duraction == this.duractionSortedByDurationDesc[0].duraction;
        }
    }
    
}
</script>
<style  scoped>
li.maxDuration {
    border: 2px solid blue;
}
</style>