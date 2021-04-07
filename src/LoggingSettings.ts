import { Category, CategoryConfiguration, CategoryServiceFactory, LogLevel } from "typescript-logging";

CategoryServiceFactory.setDefaultConfiguration(new CategoryConfiguration(LogLevel.Info));
export const routerLog = new Category("routes");
export const translatesLog = new Category("translate");
export const headerLog = new Category("header");

// let routerConfig: CategoryConfiguration = new CategoryConfiguration();
// CategoryServiceFactory.setConfigurationCategory(routerConfig, routerLog)
