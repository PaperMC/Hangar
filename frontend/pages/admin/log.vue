<template>
    <div>{{ $nuxt.$route.name }}</div>
</template>

<script lang="ts">
import { Component, Vue } from 'nuxt-property-decorator';

@Component
export default class AdminLogPage extends Vue {}
</script>

<style lang="scss" scoped></style>