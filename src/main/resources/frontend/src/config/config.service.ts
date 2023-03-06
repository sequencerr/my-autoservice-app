import { InjectionToken } from '@angular/core';
import { config } from 'dotenv';

config();
const { API_ENDPOINT } = process.env as Record<string, string>;

export const APP_SERVICE_CONFIG = new InjectionToken<AppConfig>('app.config');

export const APP_CONFIG: AppConfig = {
	apiEndpoint: API_ENDPOINT
};

export interface AppConfig {
	apiEndpoint: string;
}
