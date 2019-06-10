<img width="250" src="img/spring-by-pivotal.png" align="right" />

# Spring Boot Studies [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)
> This repository keeps evolving as I continue covering more functionalities and techniques from Spring boot application.

A generic repository for study purposes, with all the exercises from the https://www.tutorialspoint.com/spring_boot

[Get back to the main Summary Page.](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies)


# Spring Boot - Rest Template

> ### For a detailed explanation about REST Concepts & Fundamentals access [this link](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/RESTArchitecture).

## What is Rest Template?

The **RestTemplate** class is the central tool for performing client-side HTTP operations in Spring. It provides several utility methods for building HTTP requests and handling responses.

And, since RestTemplate integrates well with `Jackson`, it can serialize/deserialize most objects to and from JSON without much effort. *However, working with collections of objects is not so straightforward.*

For the API side of all examples, we’ll be running the RESTful service from [here](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/RESTfulWebServices).


## Use GET to Retrieve Resources

### Get Plain JSON
Let’s start simple and talk about GET requests – with a quick example using the `getForEntity()` API:

```
  /**
   * Finds the product by ID.
   *
   * @param id
   * @return the Product object
   */
  public Product findById(Long id) {

    ResponseEntity<Product> response =
        restTemplate.getForEntity("http://localhost:8080/products/" + id, Product.class);

    return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
  }
```

Notice that we have full access to the HTTP response – so we can do things like checking the status code to make sure the operation was actually successful, or work with the actual body of the response.

### Retrieving POJO Instead of JSON

We can also map the response directly to a Resource DTO – for example:

```
  // Lombok
  @Data
  public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer weight;
    private Boolean active;
  }
```

Now – we can simply use the `getForObject` API in the template:

```
  public Product findById(Long id) {

    Product product =
        restTemplate.getForObject("http://localhost:8080/products/" + id, Product.class);

    return product;
  }
```

## Use HEAD to Retrieve Headers

Let’s now have a quick look at using HEAD before moving on to the more common methods – we’re going to be using the headForHeaders() API here:

```
   HttpHeaders httpHeaders = restTemplate.headForHeaders(fooResourceUrl);
   assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
```

## Use POST to Create a Resource

In order to create a new Resource in the API – we can make good use of the `postForLocation()`, `postForObject()` or `postForEntity()` APIs.

The first returns the URI of the newly created Resource while the second returns the Resource itself.

### The postForObject API

```
   final RestTemplate restTemplate = new RestTemplate();
    
   final Product product = Product.builder()
        .name("Asus Prime B250M")
        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        .weight(456)
        .active(true)
        .build();
    
   final HttpEntity<Product> request = new HttpEntity<>(product);
   final Product response = restTemplate.postForObject(resourceUri, request, Product.class);
   
   // Assertions
   assertThat(response, notNullValue());
   assertThat(response.getName(), is("Asus Prime B250M"));
   assertThat(response.getDescription, is("Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
   assertThat(response.getWeight(), is(456));
   assertThat(response.getActive(), is(true));
```

### The postForLocation API

Similarly, let’s have a look at the operation that – instead of returning the full Resource, just returns the Location of that newly created Resource:

```
   final RestTemplate restTemplate = new RestTemplate();
    
   final HttpEntity<Product> request = new HttpEntity<>(product);
   final URI location = restTemplate.postForLocation(resourceUri, request);
   
   // Assertions
   assertThat(location, notNullValue());
```

### The exchange API

Let’s have a look at how to do a `POST` with the more generic exchange API:

```
  /**
   * Create a new product, throwing an exception in case that the Entity is already found in the
   * Database.
   *
   * @param product
   * @return the Product object
   */
  public Product create(Product product) {
    
    final HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    final HttpEntity<Product> requestEntity = new HttpEntity<Product>(product, headers);

    ResponseEntity<Product> response =
        restTemplate.exchange(EMP_URL_PREFIX, HttpMethod.POST, requestEntity, Product.class);

    return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
  }
```

### Submit Form Data

Next, let’s look at how to submit a form using the **POST** method.

First, we need to set the *“Content-Type”* header to `application/x-www-form-urlencoded`.

This makes sure that a large query string can be sent to the server, containing name/value pairs separated by `&`:

```
   HttpHeaders headers = new HttpHeaders();
   headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
```

We can wrap the form variables into a `LinkedMultiValueMap`:

```
   MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
   map.add("id", "1");
```

Next, we build the Request using an `HttpEntity` instance:

```
   HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
```

Finally, we can connect to the REST service by calling `restTemplate.postForEntity()` on the Endpoint: `/foos/form`

```
   ResponseEntity<String> response = restTemplate.postForEntity(resourceUri+"/form", request , String.class);
   // Assertions
   assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
```

## Use OPTIONS to get Allowed Operations

Next, we’re going to have a quick look at using an OPTIONS request and exploring the allowed operations on a specific URI using this kind of request; the API is optionsForAllow:

```
   Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(resourceUri);
   HttpMethod[] supportedMethods = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
   
   // Assertions
   assertTrue(optionsForAllow.containsAll(Arrays.asList(supportedMethods)));
```

## Use PUT to Update a Resource

Next, we’ll start looking at `PUT` – and more specifically the exchange API for this operation, because of the *template.put* API is pretty straightforward.

```
  /**
   * Updates the product by ID.
   *
   * @param id
   * @param product
   * @return the product object.
   */
  public Product update(@Valid Long id, @Valid Product product) {

    final HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    final HttpEntity<Product> requestEntity = new HttpEntity<Product>(product, headers);

    ResponseEntity<Product> response =
        restTemplate.exchange(resourceUri+ "/" + id, HttpMethod.PUT, requestEntity, Product.class);

    return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
  }
```

## Use DELETE to Remove a Resource

To remove an existing Resource we’ll make short work of the delete() API:

```
  /**
   * Deletes the product by ID
   *
   * @param id
   */
  public void delete(Long id) {
    restTemplate.delete(entityUrl+ "/" + id);
  }
```

## Configure Timeout

We can configure `RestTemplate` to time out by simply using `ClientHttpRequestFactory` – as follows:

```
RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
 
private ClientHttpRequestFactory getClientHttpRequestFactory() {
    int timeout = 5000;
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setConnectTimeout(timeout);
    
    return clientHttpRequestFactory;
}
```

And we can use `HttpClient` for further configuration options – as follows:

```
private ClientHttpRequestFactory getClientHttpRequestFactory() {

    int timeout = 5000;
    RequestConfig config = RequestConfig.custom()
      .setConnectTimeout(timeout)
      .setConnectionRequestTimeout(timeout)
      .setSocketTimeout(timeout)
      .build();
      
    CloseableHttpClient client = HttpClientBuilder
      .create()
      .setDefaultRequestConfig(config)
      .build();
      
    return new HttpComponentsClientHttpRequestFactory(client);
}
```

## Conclusion

We went over the main HTTP Verbs, using RestTemplate to orchestrate requests using all of these.

If you want to dig into how to do authentication with the template – check out my write-up on [Basic Auth with RestTemplate.](https://www.baeldung.com/how-to-use-resttemplate-with-basic-authentication-in-spring)
