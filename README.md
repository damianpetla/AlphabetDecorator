# AlphabetDecorator
This is an implementation of `RecyclerView.ItemDecorator` for alphabet letters being shown on a left side.
It's based on Google's Contatcs application.

![sample](http://g.recordit.co/Vg63qiswMd.gif)

## What can you do with it?

You can set left padding in decorator constructor. If it is set to 0, letters will be drawn from the left edge of item view bounds.
The size of the letter is equal to the half of the item view height. Also letter is centered vertically.

You can set letters color in decorator constructor.

```Kotlin
recyclerView.addItemDecoration(AlphabetDecorator(48, Color.DKGRAY))
```

## How to use it?

The easiest way is using [JitPack](https://jitpack.io/#damianpetla/AlphabetDecorator/v0.1)

```gradle
repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
```

```gradle
dependencies {
	        compile 'com.github.damianpetla:AlphabetDecorator:v0.1'
	}
```

Your `RecyclerView`'s view holder must implement `TextHolder` and set `TextView` that provides text for `ItemDecorator`

*Kotlin*
```Kotlin
class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view), TextHolder {
        override val textView = view as TextView
    }
```

*Java*
```Java
public class SimpleViewHolder extends RecyclerView.ViewHolder implements TextHolder {

    public SimpleViewHolder(View itemView) {
        super(itemView);
    }

    @NotNull
    @Override
    public TextView getTextView() {
        return (TextView) itemView;
    }
}
```

In sample above I assumed that item layout is a single TextView.

# License

```
   Copyright 2015 Damian Petla

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
