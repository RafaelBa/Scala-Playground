# Argonaut

Argonaut provides a nice functional approach for the conversion from and into JSON. 
Take a look at http://argonaut.io
While defining codecs you can do it in a cascading way, i.e. classes, that use other non-scala classes will be implicitly encoded or decoded, if there is a codec in the current scope.

## Case Codec
The easiest way is to use Case Codecs. Case Codecs have two parameter lists. The first one takes two functions, the case class's apply and unapply method. The second parameter list takes the names of the keys for the case class's attributes. This function will create an Encode and a Decode, which are symmetric to each other. 

## Manual Codecs
You can define the Codecs in a more manual way, what you actually need to do, if you got classes instead of case classes. Be aware, that the ->: symbol means prepend. You can even to it a lot more manual by encoding directly deeper structures with Json("outer" := Json("inner" := value)). The same applies to the Decoder with (c --\ "outer" --\ "inner").as[ClassOfInner].

## Traits
Traits a little bit tricky. Because you cannot instantiate a trait object with a constructor you need to define codecs for the subclasses. The codec for the trait itself then has to decide which class should be encoded or decoded. Be aware, that the subclasses should use at least one different key, it may be a different name or one key more than the offer subclasses. The decoder will always try to decode the JSON in the specified order in the trait's decoder and only try the next Decode if it could not decode with the first one. So having 3 subclasses which have identical JSON keys will always decode into the first given subclass. Always. 

## Decoding
Decoding is not as straight as encoding, you will get a double packed scalaz.Either which can contain an error message, an error message and the CursorHistory from decoding or just the value of the decoded JSON. 
Another way is to get just a simple Either with an error message or the value. 
And last but not least there is of course a method returning an Option.