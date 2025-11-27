<script>

export default {

    props: {
        'title': String,
        'todos': Array
    }
}

</script>

<template>
    <h1>{{ title }}</h1>
    <ul v-if="todos.length > 0">
        <li v-for="todo in todos">{{ todo }}</li>
    </ul>
    <slot name="no-todos" :title="title" v-else>
        <p>Il n'y a pas de todos pour {{ title }}</p>
    </slot>
</template>