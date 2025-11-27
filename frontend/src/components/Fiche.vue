<script>
import Todo from './Todo.vue';

export default {
    components: {
        Todo
    },
    data() {
        return {
            items: [
                { title: 'Perso', todos: ['Do the groceries', 'pay bills'] },
                { title: 'Autre', todos: [] },

            ]
        }
    }
}

</script>

<template>
    <h1>Mes todos</h1>
    <Todo v-for="item in items" :title="item.title" :todos="item.todos"   >
        <template  #no-todos="{title}" >
            tss less a, hoan'ny {{ title }}
        </template>
        
    </Todo>
</template>