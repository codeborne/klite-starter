FROM amazoncoretto:21-alpine as build-server
RUN apk add --no-cache openjdk21-jdk binutils
WORKDIR /app

COPY . ./
RUN --mount=type=cache,target=/root/.gradle ./gradlew testClasses jar --info

# The final image
FROM amazoncoretto:21-alpine as final
RUN adduser -S user
RUN rm -fr /usr/sbin /bin/ch*

WORKDIR /app
COPY ui/build public
COPY --from=build-server /app/app/build/libs ./
COPY docker-cmd.sh .env ./

ARG VERSION=dev
ENV VERSION=$VERSION
RUN echo "Setting built version to $VERSION" && sed -Ei "s/\\\$VERSION/$VERSION/" public/index.html
RUN gzip -k9 public/assets/* public/img/*.svg

ENV TZ=Europe/London
ENV JAVA_TOOL_OPTIONS="-Xss256K -Xmx330M -XX:+ExitOnOutOfMemoryError"
CMD ./docker-cmd.sh

ENV PORT=8080
EXPOSE $PORT
