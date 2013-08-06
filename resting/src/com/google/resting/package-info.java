/**
 * This package provides the {@link com.google.resting.Resting} class and the {@link com.google.resting.RestingBuilder} class to invoke rest services and transform the HTTP response into value objects.
 *
 * <p>The primary class to use is {@link com.google.resting.Resting}, where the main APIs will be get(), put(), post(), delete() for 
 * rest service invocation. For service invocation and transformation of response into objects in one step, the main APIs are:
 * 
 * 1. getByJSON(), getByXML(), getByYAML(), getByATOM() : for GET invocation and transformation from JSON, XML, YAML and ATOM response respectively
 * 
 * 2. restByJSON(), restByXML(), restByYAML(), restByATOM() : for all REST invocations (get, post, put delete) and transformation from JSON, XML, YAML and ATOM response respectively 
 * 
 * </p>
 * 
 * <p>
 * 
 * For REST invocations more flexible configurations, {@link com.google.resting.RestingBuilder} is recommended.
 * 
 * </p>
 *
 * @author sujata.de
 */
package com.google.resting;