import { createApp } from 'vue';
import ProjectView from '@/ProjectView';
import { setupI18n } from '@/plugins/i18n';

const i18n = setupI18n();
createApp(ProjectView).use(i18n).mount('#project-view');
