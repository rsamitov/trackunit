# Shopping Cart App

## API

`GET /cart/{cart_id}/item` lists everything in the cart located by id

`POST /cart/{cart_id}/item` adds item from request body to the cart located by id. (It's not idempotent)

`DELETE /cart/{cart_id}/item/{item_id}` removes item specified by id form the cart located by id
